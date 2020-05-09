package com.med.disease.tracking.app.mapper;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.med.disease.tracking.app.dto.request.SurveyRequestDTO;
import com.med.disease.tracking.app.model.Survey;

@Component
public class SubmitSurveyQuestionMapper extends Mapper {

	@Override
	protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
		SurveyRequestDTO requestDTO = (SurveyRequestDTO) objectToMap;
		Survey set = new Survey();
		set.setSurveyId(requestDTO.getSurveyId());
		set.setQuestionIds(requestDTO.getQuestionIds());
		return set;
	}

	@Override
	protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
		return null;
	}
}