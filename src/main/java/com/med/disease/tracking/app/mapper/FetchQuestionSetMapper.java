package com.med.disease.tracking.app.mapper;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.med.disease.tracking.app.dto.QuestionSetDTO;
import com.med.disease.tracking.app.dto.request.QuestionSetRequestDTO;
import com.med.disease.tracking.app.model.QuestionSet;

@Component
public class FetchQuestionSetMapper extends Mapper {

	@Override
	protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
		QuestionSetRequestDTO requestDTO = (QuestionSetRequestDTO) objectToMap;
		QuestionSet set = new QuestionSet();
		set.setSetId(requestDTO.getSetId());
		return set;
	}

	@Override
	protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
		QuestionSet set = (QuestionSet) objectToMap;
		QuestionSetDTO setDTO = new QuestionSetDTO();
		if (set != null) {
			setDTO.setSetId(set.getSetId());
			setDTO.setDescription(set.getDescription());
			setDTO.setCreated(set.getCreated());
			setDTO.setIsActive(set.getIsActive());
		}
		return setDTO;
	}
}
