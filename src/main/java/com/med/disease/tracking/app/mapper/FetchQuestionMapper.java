package com.med.disease.tracking.app.mapper;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.med.disease.tracking.app.dto.OptionDTO;
import com.med.disease.tracking.app.dto.QuestionDTO;
import com.med.disease.tracking.app.dto.request.QuestionRequestDTO;
import com.med.disease.tracking.app.model.Option;
import com.med.disease.tracking.app.model.Question;

@Component
public class FetchQuestionMapper extends Mapper {

	@Override
	protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
		QuestionRequestDTO requestDTO = (QuestionRequestDTO) objectToMap;
		Question question = new Question();
		question.setQuestionId(requestDTO.getQuestionId());
		return question;
	}

	@Override
	protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
		Question question = (Question) objectToMap;
		QuestionDTO questionDTO = new QuestionDTO();
		if (question != null) {
			questionDTO.setQuestionId(question.getQuestionId());
			questionDTO.setQuestion(question.getQuestion());
			questionDTO.setType(question.getType());
			if (!CollectionUtils.isEmpty(question.getOptions())) {
				questionDTO.setOptions(question.getOptions().stream().map(FetchQuestionMapper::getOptionDTO)
						.collect(Collectors.toList()));
			}
		}
		return questionDTO;
	}

	public static OptionDTO getOptionDTO(Option option) {
		OptionDTO optionDTO = null;
		if (option != null) {
			optionDTO = new OptionDTO();
			optionDTO.setOptionId(option.getOptionId());
			optionDTO.setDisplayName(option.getDisplayName());
			optionDTO.setChecked(option.getChecked());
			optionDTO.setSize(option.getSize());
			optionDTO.setRisk(option.getRisk());
		}
		return optionDTO;
	}
}
