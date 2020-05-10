package com.med.disease.tracking.app.mapper;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.med.disease.tracking.app.dto.QuestionDTO;
import com.med.disease.tracking.app.dto.SurveyDTO;
import com.med.disease.tracking.app.dto.request.SurveyRequestDTO;
import com.med.disease.tracking.app.model.Question;
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
		if (!ObjectUtils.isEmpty(survey)) {
			surveyDTO.setSurveyId(survey.getSurveyId());
			surveyDTO.setDescription(survey.getDescription());
			surveyDTO.setCreated(survey.getCreated());
			surveyDTO.setIsActive(survey.getIsActive());
			if (!CollectionUtils.isEmpty(survey.getQuestions())) {
				surveyDTO.setQuestions(survey.getQuestions().stream().map(FetchSurveyMapper::getQuestionDTO)
						.collect(Collectors.toList()));
			}
		}
		return surveyDTO;
	}

	private static QuestionDTO getQuestionDTO(Question question) {
		QuestionDTO questionDTO = null;
		if (!ObjectUtils.isEmpty(question)) {
			questionDTO = new QuestionDTO();
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
}
