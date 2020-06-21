package com.med.disease.tracking.app.service;

import java.util.List;

import com.med.disease.tracking.app.dto.SurveyDTO;
import com.med.disease.tracking.app.dto.request.SurveyRequestDTO;

public interface SurveyService {

	SurveyDTO getSurvey(SurveyRequestDTO requestDTO) throws Exception;
	
	List<SurveyDTO> getSurveys(SurveyRequestDTO requestDTO) throws Exception;

	void submitSurvey(SurveyRequestDTO requestDTO) throws Exception;

	void updateSurvey(SurveyRequestDTO requestDTO) throws Exception;

	void submitSurveyQuestions(SurveyRequestDTO requestDTO) throws Exception;
	
	SurveyDTO getSurveyQuestions(SurveyRequestDTO requestDTO) throws Exception;
	
	List<SurveyDTO> getActiveSurveys(SurveyRequestDTO requestDTO) throws Exception;
}
