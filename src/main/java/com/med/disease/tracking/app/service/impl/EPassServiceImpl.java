package com.med.disease.tracking.app.service.impl;

import com.med.disease.tracking.app.dao.AuditDAO;
import com.med.disease.tracking.app.dao.EPassDAO;
import com.med.disease.tracking.app.dao.RiskDAO;
import com.med.disease.tracking.app.dao.UserInfoDAO;
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
import com.med.disease.tracking.app.model.*;
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

	@Autowired
	AuditDAO auditDAO;

	@Autowired
	UserInfoDAO userInfoDAO;

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

        Risk risk = getRisk(ePass);
        if(!ePass.getIsAllowed()){
            riskDAO.deleteRisk(risk);
            ePassDAO.deleteEpasses(ePass);
            if(!ePass.getFromDate().isAfter(LocalDate.now())){
					Audit audit = getAuditObj(ePass);
					auditDAO.submitAudit(audit);
            }
        }
	}

	private Risk getRisk(EPass ePass) {
		Risk risk = new Risk();
		User user = new User();
		Survey survey = new Survey();
		user.setUserId(ePass.getUser().getUserId());
		survey.setSurveyId(ePass.getSurvey().getSurveyId());
		risk.setUser(user);
		risk.setSurvey(survey);
		return risk;
	}

	private Audit getAuditObj(EPass ePass) {
		User user = new User();
		user.setUserId(ePass.getUser().getUserId());

		Survey survey = new Survey();
		survey.setSurveyId(ePass.getSurvey().getSurveyId());

		User createdBy = new User();
		createdBy.setUserId(ePass.getUser().getUserId());

		Audit audit = new Audit();
		audit.setUser(user);
		audit.setSurvey(survey);
		audit.setIsAllowed(true);
		audit.setToDate(ePass.getToDate().isBefore(LocalDate.now()) ? ePass.getToDate() : LocalDate.now());
		audit.setCreatedBy(createdBy);
		audit.setFromDate(ePass.getFromDate());
		return audit;
	}

	@Override
	public EPassDTO fetchEPass(EPassRequestDTO requestDTO) throws Exception {
		EPass ePass = (EPass) fetchEPassMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		List<EPass> ePassUser = ePassDAO.searchUser(ePass);
		Optional<Risk> userRisk = getRisk(requestDTO);
		if(userRisk.isPresent()) {
			if (!ePassUser.isEmpty() && ePassUser.get(0).getToDate().isBefore(LocalDate.now())) {
				return null; // Return 204 No Content - Take self assessment
			}
			if (ePassUser.isEmpty() || !ePassUser.get(0).getIsAllowed()) {
				return new EPassDTO(); // Return 200 Blank Response - Awaiting manager Approval
			}
		} else return null; // Return 204 No Content - Take self assessment
		checkEpassExpiry(ePassUser);
		List<User> user = userInfoDAO.searchUser(ePassUser.get(0).getUser());
		ePassUser.get(0).setUser(user.get(0));
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
		survey.setSurveyId(requestDTO.getSurveyId());
		User user = new User();
		user.setUserId(requestDTO.getUserId());
		risk.setUser(user);
		risk.setSurvey(survey);
		return riskDAO.getRisk(risk);
	}
}
