package com.med.disease.tracking.app.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.med.disease.tracking.app.exception.DatabaseException;
import com.med.disease.tracking.app.model.Question;

@Repository
public class QuestionaryDAO extends BaseDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuestionaryDAO.class);

	public Question getQuestion(Question question) throws DatabaseException {
		try {
			return getSqlSession().selectOne("Questionaries.getQuestionInfo", question);
		} catch (Exception e) {
			LOGGER.error("DatabaseException : {}", e.getMessage());
			throw new DatabaseException(e.getMessage());
		}
	}
}
