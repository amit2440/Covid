package com.med.disease.tracking.app.mapper;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.med.disease.tracking.app.dto.QuestionDTO;
import com.med.disease.tracking.app.dto.QuestionRequestDTO;
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
			questionDTO.setControl(question.getControl());
		}
		return questionDTO;
	}
}
