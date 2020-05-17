package com.med.disease.tracking.app.mapper;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.med.disease.tracking.app.dto.request.OptionRequestDTO;
import com.med.disease.tracking.app.dto.request.QuestionRequestDTO;
import com.med.disease.tracking.app.model.Option;
import com.med.disease.tracking.app.model.Question;

@Component
public class SubmitQuestionMapper extends Mapper {

	@Override
	protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
		QuestionRequestDTO requestDTO = (QuestionRequestDTO) objectToMap;
		Question question = new Question();

		if (!ObjectUtils.isEmpty(requestDTO)) {
			question.setQuestion(requestDTO.getQuestion());
			question.setType(requestDTO.getType());
			if (!CollectionUtils.isEmpty(requestDTO.getOptions())) {
				question.setOptions(requestDTO.getOptions().stream().map(SubmitQuestionMapper::getOption).collect(Collectors.toList()));
			}
		}
		return question;
	}

	private static Option getOption(OptionRequestDTO requestDTO) {
		Option option = null;
		if (!ObjectUtils.isEmpty(requestDTO)) {
			option = new Option();
			option.setDisplayName(requestDTO.getDisplayName());
			option.setChecked(requestDTO.getChecked());
			option.setSize(requestDTO.getSize());
			option.setRisk(requestDTO.getRisk());
		}
		return option;
	}

	@Override
	protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
		return null;
	}
}
