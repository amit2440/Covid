package com.med.disease.tracking.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.med.disease.tracking.app.dao.QuestionDAO;
import com.med.disease.tracking.app.dto.QuestionDTO;
import com.med.disease.tracking.app.dto.request.QuestionRequestDTO;
import com.med.disease.tracking.app.exception.CovidAppException;
import com.med.disease.tracking.app.mapper.FetchQuestionMapper;
import com.med.disease.tracking.app.mapper.MappingTypeEnum;
import com.med.disease.tracking.app.mapper.SubmitQuestionMapper;
import com.med.disease.tracking.app.model.Option;
import com.med.disease.tracking.app.model.Question;
import com.med.disease.tracking.app.model.QuestionOption;
import com.med.disease.tracking.app.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuestionServiceImpl.class);

	@Autowired
	private FetchQuestionMapper fetchQuestionMapper;

	@Autowired
	private SubmitQuestionMapper submitQuestionMapper;

	@Autowired
	private QuestionDAO questionDAO;

	@Override
	@Transactional
	public QuestionDTO getQuestion(QuestionRequestDTO requestDTO) throws Exception {
		Question question = (Question) fetchQuestionMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		Question result = questionDAO.getQuestion(question);
		QuestionDTO questionDTO = (QuestionDTO) fetchQuestionMapper.map(result, MappingTypeEnum.MAPTORESPONSE, null);
		return questionDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void addQuestion(QuestionRequestDTO requestDTO) throws Exception {
		Question question = (Question) submitQuestionMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		if (questionDAO.addQuestion(question) <= 0) {
			LOGGER.error("Unable to insert Question");
			throw new CovidAppException("Insert Question failed");
		}
		QuestionOption questionOp = new QuestionOption();
		Question q = new Question();
		q.setQuestionId(question.getQuestionId());
		questionOp.setQuestion(q);
		
		for (Option option : question.getOptions()) {
			if (questionDAO.addOption(option) <= 0) {
				LOGGER.error("Unable to insert Option");
				throw new CovidAppException("Insert Option failed");
			}
			Option op = new Option();
			op.setOptionId(option.getOptionId());
			questionOp.setOption(op);
			
			if (questionDAO.addQuestionOption(questionOp) <= 0) {
				LOGGER.error("Unable to insert Question Option");
				throw new CovidAppException("Insert Question Option failed");
			}
		}
	}
}
