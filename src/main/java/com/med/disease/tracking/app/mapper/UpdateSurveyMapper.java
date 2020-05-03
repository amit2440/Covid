package com.med.disease.tracking.app.mapper;

import java.util.Map;

import com.med.disease.tracking.app.model.Survey;
import org.springframework.stereotype.Component;

import com.med.disease.tracking.app.dto.request.SurveyRequestDTO;

@Component
public class UpdateSurveyMapper extends Mapper {

	@Override
	protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
		SurveyRequestDTO requestDTO = (SurveyRequestDTO) objectToMap;
		Survey set = new Survey();
		set.setSurveyId(requestDTO.getSurveyId());
		set.setDescription(requestDTO.getDescription());
		set.setIsActive(requestDTO.getIsActive());
		return set;
	}

	@Override
	protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
		return null;
	}
}
