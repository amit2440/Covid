package com.med.disease.tracking.app.service.impl;

import com.med.disease.tracking.app.dao.EPassDAO;
import com.med.disease.tracking.app.dao.RiskDAO;
import com.med.disease.tracking.app.dto.EPassDTO;
import com.med.disease.tracking.app.dto.EPassRequestDTO;
import com.med.disease.tracking.app.dto.RiskDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.dto.request.FetchRiskRequestDTO;
import com.med.disease.tracking.app.exception.CovidAppException;
import com.med.disease.tracking.app.exception.DatabaseException;
import com.med.disease.tracking.app.mapper.FetchEPassMapper;
import com.med.disease.tracking.app.mapper.MappingTypeEnum;
import com.med.disease.tracking.app.mapper.SubmitEPassMapper;
import com.med.disease.tracking.app.model.EPass;
import com.med.disease.tracking.app.model.Risk;
import com.med.disease.tracking.app.model.Survey;
import com.med.disease.tracking.app.model.User;
import com.med.disease.tracking.app.service.EPassService;
import com.med.disease.tracking.app.service.RiskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EPassServiceImpl implements EPassService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EPassServiceImpl.class);

	@Autowired
	private SubmitEPassMapper submitEPassMapper;

	@Autowired
	private FetchEPassMapper fetchEPassMapper;

	@Autowired
	private EPassDAO ePassDAO;

	@Autowired
	RiskDAO riskDAO;

	@Override
	@Transactional(readOnly = false)
	public void submitEPass(EPassRequestDTO requestDTO) throws Exception {
		EPass ePass = (EPass) submitEPassMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		// fetching logged in user
		UserDetailsImpl ud = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User crUser = new User();
		crUser.setUserId(ud.getUserId());
		ePass.setCreatedBy(crUser);

		if (ePassDAO.submitEPass(ePass) <= 0) {
			LOGGER.error("Unable to insert ePass for userId=" + requestDTO.getUserId());
			throw new CovidAppException("Insert EPass failed");
		}
	}

	@Override
	public EPassDTO fetchEPass(EPassRequestDTO requestDTO) throws Exception {
		EPass ePass = (EPass) fetchEPassMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		List<EPass> ePassUser = ePassDAO.searchUser(ePass);
		Optional<Risk> userRisk = getRisk(requestDTO);
		if(userRisk.isPresent()){
			if(ePassUser.isEmpty() || ePassUser.get(0).getToDate().isBefore(LocalDate.now())){
				return new EPassDTO(); // Return 200 Blank Response - Awaiting manager Approval
			}
		} else return null; // Return 204 No Content - Take self assessment
//		if(!ePassUser.isEmpty() && ePassUser.get(0).getToDate().isBefore(LocalDate.now())){
//				return null; // Return 204 No Content - Take self assessment
//		}
		checkEpassExpiry(ePassUser);
		return (EPassDTO) fetchEPassMapper.map(ePassUser, MappingTypeEnum.MAPTORESPONSE, null);
	}

	private void checkEpassExpiry(List<EPass> ePassUser) {
		if(!ePassUser.isEmpty()) {
			boolean state = !ObjectUtils.isEmpty(ePassUser) && ePassUser.get(0).getIsAllowed();
			if (state) {
				LocalDate toDte = ePassUser.get(0).getToDate();
				LocalDate frmDte = ePassUser.get(0).getFromDate();
				if (toDte.isBefore(LocalDate.now()) || frmDte.isAfter(LocalDate.now())) {
					ePassUser.get(0).setIsAllowed(false);
				}
			}
		}
	}

	private Optional<Risk> getRisk(EPassRequestDTO requestDTO) throws DatabaseException {
		Risk risk = new Risk();
		Survey survey = new Survey();
		survey.setSurveyId(requestDTO.getUserId());
		User user = new User();
		user.setUserId(requestDTO.getUserId());
		risk.setUser(user);
		risk.setSurvey(survey);
		return riskDAO.getRisk(risk);
	}
}
