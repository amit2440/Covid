package com.med.disease.tracking.app.service;

import com.med.disease.tracking.app.dto.QuestionDTO;
import com.med.disease.tracking.app.dto.SurveyDTO;
import com.med.disease.tracking.app.dto.request.QuestionRequestDTO;
import com.med.disease.tracking.app.dto.request.SurveyRequestDTO;


public interface QuestionaryService {
	
	QuestionDTO getQuestion(QuestionRequestDTO requestDTO) throws Exception;
	
	SurveyDTO getSurvey(SurveyRequestDTO requestDTO) throws Exception;
	
	void submitSurvey(SurveyRequestDTO requestDTO) throws Exception;
	
	void updateSurvey(SurveyRequestDTO requestDTO) throws Exception;
}
