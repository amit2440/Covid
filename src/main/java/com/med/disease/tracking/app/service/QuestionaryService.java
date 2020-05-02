package com.med.disease.tracking.app.service;

import com.med.disease.tracking.app.dto.QuestionDTO;
import com.med.disease.tracking.app.dto.QuestionSetDTO;
import com.med.disease.tracking.app.dto.request.QuestionRequestDTO;
import com.med.disease.tracking.app.dto.request.QuestionSetRequestDTO;


public interface QuestionaryService {
	
	QuestionDTO getQuestion(QuestionRequestDTO requestDTO) throws Exception;
	
	QuestionSetDTO getQuestionSet(QuestionSetRequestDTO requestDTO) throws Exception;
	
	void submitQuestionSet(QuestionSetRequestDTO requestDTO) throws Exception;
	
	void updateQuestionSet(QuestionSetRequestDTO requestDTO) throws Exception;
}
