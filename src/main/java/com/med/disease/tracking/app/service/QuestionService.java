package com.med.disease.tracking.app.service;

import com.med.disease.tracking.app.dto.QuestionDTO;
import com.med.disease.tracking.app.dto.request.QuestionRequestDTO;

public interface QuestionService {

	QuestionDTO getQuestion(QuestionRequestDTO requestDTO) throws Exception;
	void addQuestion(QuestionRequestDTO requestDTO) throws Exception;
}
