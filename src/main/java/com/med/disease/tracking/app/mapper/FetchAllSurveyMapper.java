package com.med.disease.tracking.app.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.med.disease.tracking.app.dto.SurveyDTO;
import com.med.disease.tracking.app.dto.request.SurveyRequestDTO;
import com.med.disease.tracking.app.model.Survey;

@Component
public class FetchAllSurveyMapper extends Mapper {

	@Override
	protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
		SurveyRequestDTO requestDTO = (SurveyRequestDTO) objectToMap;
		Survey survey = new Survey();
		survey.setSurveyId(requestDTO.getSurveyId());
		return survey;
	}

	@Override
	protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
		List<Survey> surveys = (List<Survey>) objectToMap;
		List<SurveyDTO> surveyDTOs = new ArrayList<>();
		if (!CollectionUtils.isEmpty(surveys)) {
			surveyDTOs = surveys.stream().map(FetchAllSurveyMapper::getSurveyDTO).collect(Collectors.toList());
		}
		return surveyDTOs;
	}

	private static SurveyDTO getSurveyDTO(Survey survey) {
		SurveyDTO surveyDTO = null;
		if (!ObjectUtils.isEmpty(survey)) {
			surveyDTO = new SurveyDTO();
			surveyDTO.setSurveyId(survey.getSurveyId());
			surveyDTO.setDescription(survey.getDescription());
			surveyDTO.setCreated(survey.getCreated());
			surveyDTO.setIsActive(survey.getIsActive());
		}
		return surveyDTO;
	}
}
