package com.med.disease.tracking.app.mapper;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.med.disease.tracking.app.dto.request.QuestionSetRequestDTO;
import com.med.disease.tracking.app.model.QuestionSet;

@Component
public class SubmitQuestionSetMapper extends Mapper {

	@Override
	protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
		QuestionSetRequestDTO requestDTO = (QuestionSetRequestDTO) objectToMap;
		QuestionSet set = new QuestionSet();
		set.setDescription(requestDTO.getDescription());
		return set;
	}

	@Override
	protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
		return null;
	}
}