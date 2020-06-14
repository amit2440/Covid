package com.med.disease.tracking.app.mapper;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.med.disease.tracking.app.dto.EPassRequestDTO;
import com.med.disease.tracking.app.model.EPass;
import com.med.disease.tracking.app.model.Survey;
import com.med.disease.tracking.app.model.User;

@Component
public class SubmitEPassMapper extends Mapper {

	@Override
	protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
		EPassRequestDTO requestDTO = (EPassRequestDTO) objectToMap;

		if (ObjectUtils.isEmpty(requestDTO))
			return null;

		User user = new User();
		user.setUserId(requestDTO.getUserId());

		Survey survey = new Survey();
		survey.setSurveyId(requestDTO.getSurveyId());

		User createdBy = new User();
		createdBy.setUserId(requestDTO.getUserId());

		EPass ePass = new EPass();
		ePass.setUser(user);
		ePass.setSurvey(survey);
		ePass.setIsAllowed(requestDTO.getIsAllowed());
		ePass.setToDate(requestDTO.getToDate());
		ePass.setCreatedBy(createdBy);
		ePass.setFromDate(requestDTO.getFromDate());
		return ePass;
	}

	@Override
	protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
		return null;
	}
}
