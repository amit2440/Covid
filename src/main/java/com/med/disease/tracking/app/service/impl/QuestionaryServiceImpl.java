package com.med.disease.tracking.app.service.impl;

import com.med.disease.tracking.app.dto.SurveyDTO;
import com.med.disease.tracking.app.model.Survey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.med.disease.tracking.app.dao.QuestionaryDAO;
import com.med.disease.tracking.app.dto.QuestionDTO;
import com.med.disease.tracking.app.dto.request.QuestionRequestDTO;
import com.med.disease.tracking.app.dto.request.SurveyRequestDTO;
import com.med.disease.tracking.app.exception.CovidAppException;
import com.med.disease.tracking.app.mapper.FetchQuestionMapper;
import com.med.disease.tracking.app.mapper.FetchQuestionSetMapper;
import com.med.disease.tracking.app.mapper.MappingTypeEnum;
import com.med.disease.tracking.app.mapper.SubmitQuestionSetMapper;
import com.med.disease.tracking.app.mapper.UpdateQuestionSetMapper;
import com.med.disease.tracking.app.model.Question;
import com.med.disease.tracking.app.service.QuestionaryService;

@Service
public class QuestionaryServiceImpl implements QuestionaryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuestionaryServiceImpl.class);
	
	@Autowired
	FetchQuestionMapper fetchQuestionMapper;

	@Autowired
	FetchQuestionSetMapper fetchQuestionSetMapper;
	
	@Autowired
	SubmitQuestionSetMapper submitQuestionSetMapper;
	
	@Autowired
	UpdateQuestionSetMapper updateQuestionSetMapper;

	@Autowired
	QuestionaryDAO questionaryDAO;

	@Override
	@Transactional(readOnly = true)
	public QuestionDTO getQuestion(QuestionRequestDTO requestDTO) throws Exception {
		Question question = (Question) fetchQuestionMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		Question result = questionaryDAO.getQuestion(question);
		QuestionDTO questionDTO = (QuestionDTO) fetchQuestionMapper.map(result, MappingTypeEnum.MAPTORESPONSE, null);
		return questionDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public SurveyDTO getQuestionSet(SurveyRequestDTO requestDTO) throws Exception {
		Survey Survey = (Survey) fetchQuestionSetMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		Survey result = questionaryDAO.getSurvey(Survey);
		SurveyDTO SurveyDTO = (SurveyDTO) fetchQuestionSetMapper.map(result, MappingTypeEnum.MAPTORESPONSE, null);
		return SurveyDTO;
	}
	
	@Override
	@Transactional(readOnly = false)
	public void submitQuestionSet(SurveyRequestDTO requestDTO) throws Exception {
		Survey Survey = (Survey) submitQuestionSetMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		if(questionaryDAO.submitSurvey(Survey) <= 0) {
			LOGGER.error("Unable to insert Question set");
			throw new CovidAppException("Insert Question Set failed");
		}
	}
	
	@Override
	@Transactional(readOnly = false)
	public void updateQuestionSet(SurveyRequestDTO requestDTO) throws Exception {
		Survey Survey = (Survey) updateQuestionSetMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		if(questionaryDAO.updateSurvey(Survey) <= 0) {
			LOGGER.error("Unable to update Question set");
			throw new CovidAppException("Update Question Set failed");
		}
	}
}
