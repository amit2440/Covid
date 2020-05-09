package com.med.disease.tracking.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.med.disease.tracking.app.dao.SurveyDAO;
import com.med.disease.tracking.app.dto.SurveyDTO;
import com.med.disease.tracking.app.dto.request.SurveyRequestDTO;
import com.med.disease.tracking.app.exception.CovidAppException;
import com.med.disease.tracking.app.mapper.FetchSurveyMapper;
import com.med.disease.tracking.app.mapper.MappingTypeEnum;
import com.med.disease.tracking.app.mapper.SubmitSurveyMapper;
import com.med.disease.tracking.app.mapper.SubmitSurveyQuestionMapper;
import com.med.disease.tracking.app.mapper.UpdateSurveyMapper;
import com.med.disease.tracking.app.model.Survey;
import com.med.disease.tracking.app.service.SurveyService;

@Service
public class SurveyServiceImpl implements SurveyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SurveyServiceImpl.class);
	
	@Autowired
	private FetchSurveyMapper fetchSurveyMapper;

	@Autowired
	private SubmitSurveyMapper submitSurveyMapper;
	
	@Autowired
	private SubmitSurveyQuestionMapper submitSurveyQuestionMapper;

	@Autowired
	private UpdateSurveyMapper updateSurveyMapper;

	@Autowired
	private SurveyDAO surveyDAO;

	@Override
	@Transactional
	public SurveyDTO getSurvey(SurveyRequestDTO requestDTO) throws Exception {
		Survey survey = (Survey) fetchSurveyMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		Survey result = surveyDAO.getSurvey(survey);
		SurveyDTO SurveyDTO = (SurveyDTO) fetchSurveyMapper.map(result, MappingTypeEnum.MAPTORESPONSE, null);
		return SurveyDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void submitSurvey(SurveyRequestDTO requestDTO) throws Exception {
		Survey survey = (Survey) submitSurveyMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		if (surveyDAO.submitSurvey(survey) <= 0) {
			LOGGER.error("Unable to insert Survey");
			throw new CovidAppException("Insert Survey failed");
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void updateSurvey(SurveyRequestDTO requestDTO) throws Exception {
		Survey survey = (Survey) updateSurveyMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		if (surveyDAO.updateSurvey(survey) <= 0) {
			LOGGER.error("Unable to update Survey");
			throw new CovidAppException("Update Survey failed");
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void submitSurveyQuestions(SurveyRequestDTO requestDTO) throws Exception {
		Survey survey = (Survey) submitSurveyQuestionMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);

		for(Integer questionId : new ArrayList<>(survey.getQuestionIds().stream().collect(Collectors.toSet()))) {
			Map<String, Integer> mapper = new HashMap<>();
			mapper.put("surveyId", survey.getSurveyId());
			mapper.put("questionId", questionId);
			
			if (surveyDAO.submitSurveyQuestions(mapper) <= 0) {
				LOGGER.error("Unable to insert Survey Questions for questionId="+questionId);
				throw new CovidAppException("Insert Survey Questions failed");
			}
		}
	}

	@Override
	public SurveyDTO getSurveyQuestions(SurveyRequestDTO requestDTO) throws Exception {
		Survey survey = (Survey) fetchSurveyMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		return (SurveyDTO) fetchSurveyMapper.map(surveyDAO.getSurveyQuestions(survey), MappingTypeEnum.MAPTORESPONSE, null);
	}
}
