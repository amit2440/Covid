package com.med.disease.tracking.app.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.med.disease.tracking.app.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.med.disease.tracking.app.dto.EPassDTO;
import com.med.disease.tracking.app.dto.EPassRequestDTO;
import com.med.disease.tracking.app.model.EPass;
import com.med.disease.tracking.app.model.Survey;
import com.med.disease.tracking.app.model.User;

@Component
public class FetchEPassMapper extends Mapper {

	@Override
	protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
		EPassRequestDTO requestDTO = (EPassRequestDTO) objectToMap;

		if (ObjectUtils.isEmpty(requestDTO))
			return null;

		User user = new User();
		user.setUserId(requestDTO.getUserId());

		Survey survey = new Survey();
		survey.setSurveyId(requestDTO.getSurveyId());

		EPass ePass = new EPass();
		ePass.setUser(user);
		ePass.setSurvey(survey);
		return ePass;
	}

	@Override
	protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
		List<EPass> ePass = (List<EPass>) objectToMap;
		EPassDTO ePassDTO = new EPassDTO();
		ePassDTO.setToDate(ePass.get(0).getToDate());
		ePassDTO.setFromDate(ePass.get(0).getFromDate());
		ePassDTO.setIsAllowed(ePass.get(0).getIsAllowed());
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName(ePass.get(0).getUser().getFirstName());
		userDTO.setLastName(ePass.get(0).getUser().getLastName());
		ePassDTO.setUser(userDTO);
		return ePassDTO;
	}
}
