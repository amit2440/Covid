package com.med.disease.tracking.app.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.med.disease.tracking.app.exception.DatabaseException;
import com.med.disease.tracking.app.model.Question;

@Repository
public class QuestionDAO extends BaseDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuestionDAO.class);

	public Question getQuestion(Question question) throws DatabaseException {
		try {
			return getSqlSession().selectOne("Question.getQuestionInfo", question);
		} catch (Exception e) {
			LOGGER.error("DatabaseException : {}", e.getMessage());
			throw new DatabaseException(e.getMessage());
		}
	}
}
