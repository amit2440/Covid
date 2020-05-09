package com.med.disease.tracking.app.dao;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.med.disease.tracking.app.exception.DatabaseException;
import com.med.disease.tracking.app.model.Survey;

@Repository
public class SurveyDAO extends BaseDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(SurveyDAO.class);

	public Survey getSurvey(Survey survey) throws DatabaseException {
		try {
			return getSqlSession().selectOne("Survey.getSurveyInfo", survey);
		} catch (Exception e) {
			LOGGER.error("DatabaseException : {}", e.getMessage());
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Survey getSurveyQuestions(Survey survey) throws DatabaseException {
		try {
			return getSqlSession().selectOne("Survey.getSurveyQuestions", survey);
		} catch (Exception e) {
			LOGGER.error("DatabaseException : {}", e.getMessage());
			throw new DatabaseException(e.getMessage());
		}
	}

	public int submitSurvey(Survey survey) throws DatabaseException {
		try {
			return getSqlSession().insert("Survey.submitSurvey", survey);
		} catch (Exception e) {
			LOGGER.error("DatabaseException : {}", e.getMessage());
			throw new DatabaseException(e.getMessage());
		}
	}

	public int updateSurvey(Survey survey) throws DatabaseException {
		try {
			return getSqlSession().insert("Survey.updateSurvey", survey);
		} catch (Exception e) {
			LOGGER.error("DatabaseException : {}", e.getMessage());
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public int submitSurveyQuestions(Map<String, Integer> mapper) throws DatabaseException {
		try {
			return getSqlSession().insert("Survey.submitSurveyQuestions", mapper);
		} catch (Exception e) {
			LOGGER.error("DatabaseException : {}", e.getMessage());
			throw new DatabaseException(e.getMessage());
		}
	}
}
