package com.med.disease.tracking.app.service;

import com.med.disease.tracking.app.dto.QuestionDTO;
import com.med.disease.tracking.app.dto.QuestionRequestDTO;


public interface QuestionaryService {
	
	QuestionDTO getQuestion(QuestionRequestDTO requestDTO) throws Exception;
}
