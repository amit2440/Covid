package com.med.disease.tracking.app.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.exception.DatabaseException;
import com.med.disease.tracking.app.model.Database;
import com.med.disease.tracking.app.model.Option;
import com.med.disease.tracking.app.model.Question;
import com.med.disease.tracking.app.model.QuestionOption;

@Repository
public class QuestionDAO extends BaseDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuestionDAO.class);

	public Question getQuestion(Question question) throws DatabaseException {
		try {
			return getSqlSession().selectOne("Question.getQuestion", question);
		} catch (Exception e) {
			LOGGER.error("DatabaseException : {}", e.getMessage());
			throw new DatabaseException(e.getMessage());
		}
	}

	public int addQuestion(Question question) throws DatabaseException {
		try {
			int questionId = getSqlSession().selectOne("Common.nextVal",
					new Database(Constant.Database.SCHEMA_DEFAULT, Constant.Database.TABLE_QUESTION));
			question.setQuestionId(questionId);
			return getSqlSession().insert("Question.addQuestion", question);
		} catch (Exception e) {
			LOGGER.error("DatabaseException : {}", e.getMessage());
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public int addQuestionOption(QuestionOption questionOption) throws DatabaseException {
		try {
			return getSqlSession().insert("Question.addQuestionOption", questionOption);
		} catch (Exception e) {
			LOGGER.error("DatabaseException : {}", e.getMessage());
			throw new DatabaseException(e.getMessage());
		}
	}

	public int addOption(Option option) throws DatabaseException {
		try {
			int optionId = getSqlSession().selectOne("Common.nextVal", new Database(Constant.Database.SCHEMA_DEFAULT,Constant.Database.TABLE_OPTION ));
			option.setOptionId(optionId);
			return getSqlSession().insert("Question.addOption", option);
		} catch (Exception e) {
			LOGGER.error("DatabaseException : {}", e.getMessage());
			throw new DatabaseException(e.getMessage());
		}
	}
}
