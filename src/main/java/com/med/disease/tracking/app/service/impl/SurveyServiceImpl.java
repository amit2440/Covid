package com.med.disease.tracking.app.service.impl;

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
import com.med.disease.tracking.app.mapper.UpdateSurveyMapper;
import com.med.disease.tracking.app.model.Survey;
import com.med.disease.tracking.app.service.SurveyService;

@Service
public class SurveyServiceImpl implements SurveyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SurveyServiceImpl.class);
	
	@Autowired
	FetchSurveyMapper fetchSurveyMapper;

	@Autowired
	SubmitSurveyMapper submitSurveyMapper;

	@Autowired
	UpdateSurveyMapper updateSurveyMapper;

	@Autowired
	SurveyDAO surveyDAO;

	@Override
	@Transactional(readOnly = true)
	public SurveyDTO getSurvey(SurveyRequestDTO requestDTO) throws Exception {
		Survey Survey = (Survey) fetchSurveyMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		Survey result = surveyDAO.getSurvey(Survey);
		SurveyDTO SurveyDTO = (SurveyDTO) fetchSurveyMapper.map(result, MappingTypeEnum.MAPTORESPONSE, null);
		return SurveyDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void submitSurvey(SurveyRequestDTO requestDTO) throws Exception {
		Survey Survey = (Survey) submitSurveyMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		if (surveyDAO.submitSurvey(Survey) <= 0) {
			LOGGER.error("Unable to insert Question set");
			throw new CovidAppException("Insert Question Set failed");
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void updateSurvey(SurveyRequestDTO requestDTO) throws Exception {
		Survey Survey = (Survey) updateSurveyMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		if (surveyDAO.updateSurvey(Survey) <= 0) {
			LOGGER.error("Unable to update Question set");
			throw new CovidAppException("Update Question Set failed");
		}
	}
}
