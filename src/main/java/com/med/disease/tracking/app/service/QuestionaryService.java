package com.med.disease.tracking.app.service;

import com.med.disease.tracking.app.dto.QuestionDTO;
import com.med.disease.tracking.app.dto.SurveyDTO;
import com.med.disease.tracking.app.dto.request.QuestionRequestDTO;
import com.med.disease.tracking.app.dto.request.SurveyRequestDTO;


public interface QuestionaryService {
	
	QuestionDTO getQuestion(QuestionRequestDTO requestDTO) throws Exception;
	
	SurveyDTO getQuestionSet(SurveyRequestDTO requestDTO) throws Exception;
	
	void submitQuestionSet(SurveyRequestDTO requestDTO) throws Exception;
	
	void updateQuestionSet(SurveyRequestDTO requestDTO) throws Exception;
}
