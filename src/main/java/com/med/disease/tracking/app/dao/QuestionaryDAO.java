package com.med.disease.tracking.app.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.med.disease.tracking.app.exception.DatabaseException;
import com.med.disease.tracking.app.model.Question;
import com.med.disease.tracking.app.model.QuestionSet;

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
	
	public QuestionSet getQuestionSet(QuestionSet set) throws DatabaseException {
		try {
			return getSqlSession().selectOne("Questionaries.getQuestionSetInfo", set);
		} catch (Exception e) {
			LOGGER.error("DatabaseException : {}", e.getMessage());
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public int submitQuestionSet(QuestionSet set) throws DatabaseException {
		try {
			return getSqlSession().insert("Questionaries.submitQuestionSet", set);
		} catch (Exception e) {
			LOGGER.error("DatabaseException : {}", e.getMessage());
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public int updateQuestionSet(QuestionSet set) throws DatabaseException {
		try {
			return getSqlSession().insert("Questionaries.updateQuestionSet", set);
		} catch (Exception e) {
			LOGGER.error("DatabaseException : {}", e.getMessage());
			throw new DatabaseException(e.getMessage());
		}
	}
}
