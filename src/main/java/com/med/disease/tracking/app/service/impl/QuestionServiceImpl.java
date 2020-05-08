package com.med.disease.tracking.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.med.disease.tracking.app.dao.QuestionDAO;
import com.med.disease.tracking.app.dto.QuestionDTO;
import com.med.disease.tracking.app.dto.request.QuestionRequestDTO;
import com.med.disease.tracking.app.mapper.FetchQuestionMapper;
import com.med.disease.tracking.app.mapper.MappingTypeEnum;
import com.med.disease.tracking.app.model.Question;
import com.med.disease.tracking.app.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	FetchQuestionMapper fetchQuestionMapper;

	@Autowired
	QuestionDAO questionDAO;

	@Override
	@Transactional(readOnly = true)
	public QuestionDTO getQuestion(QuestionRequestDTO requestDTO) throws Exception {
		Question question = (Question) fetchQuestionMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		Question result = questionDAO.getQuestion(question);
		QuestionDTO questionDTO = (QuestionDTO) fetchQuestionMapper.map(result, MappingTypeEnum.MAPTORESPONSE, null);
		return questionDTO;
	}
}
