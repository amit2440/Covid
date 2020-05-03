package com.med.disease.tracking.app.mapper;

import java.util.Map;

import com.med.disease.tracking.app.dto.SurveyDTO;
import org.springframework.stereotype.Component;

import com.med.disease.tracking.app.dto.request.SurveyRequestDTO;
import com.med.disease.tracking.app.model.Survey;

@Component
public class FetchSurveyMapper extends Mapper {

	@Override
	protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
		SurveyRequestDTO requestDTO = (SurveyRequestDTO) objectToMap;
		Survey survey = new Survey();
		survey.setSurveyId(requestDTO.getSurveyId());
		return survey;
	}

	@Override
	protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
		Survey survey = (Survey) objectToMap;
		SurveyDTO surveyDTO = new SurveyDTO();
		if (survey != null) {
			surveyDTO.setSurveyId(survey.getSurveyId());
			surveyDTO.setDescription(survey.getDescription());
			surveyDTO.setCreated(survey.getCreated());
			surveyDTO.setIsActive(survey.getIsActive());
		}
		return surveyDTO;
	}
}
