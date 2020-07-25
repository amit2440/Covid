package com.med.disease.tracking.app.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.med.disease.tracking.app.exception.DatabaseException;
import com.med.disease.tracking.app.model.EPass;

@Repository
public class EPassDAO extends BaseDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(EPassDAO.class);

	public int submitEPass(EPass ePass) throws DatabaseException {
		try {
			return getSqlSession().insert("EPass.submitEPass", ePass);
		} catch (Exception e) {
			LOGGER.error("DatabaseException : {}", e.getMessage());
			throw new DatabaseException(e.getMessage());
		}
	}

	public List<EPass> searchUser(EPass ePass) throws Exception {
		try {
			return getSqlSession().selectList("EPass.searchEPass", ePass);
		} catch (Exception exception) {
			LOGGER.error("Database Exception :{}", exception.getMessage());
			throw new DatabaseException(exception.getMessage());
		}
	}

	public int deleteEpasses(EPass ePass) throws DatabaseException {
		try {
			return getSqlSession().delete("EPass.deleteEpasses", ePass);
		} catch (Exception e) {
			LOGGER.error("DatabaseException : {}", e.getMessage());
			throw new DatabaseException(e.getMessage());
		}
	}
	
	
	public List<EPass>  allowedEpassReport(EPass ePass) throws Exception {
		try {
			return getSqlSession().selectList("EPass.allowedEpassReport", ePass);
		} catch (Exception exception) {
			LOGGER.error("Database Exception :{}", exception.getMessage());
			throw new DatabaseException(exception.getMessage());
		}
	}

}