package com.med.disease.tracking.app.dao;

import com.med.disease.tracking.app.exception.DatabaseException;
import com.med.disease.tracking.app.model.Audit;
import com.med.disease.tracking.app.model.EPass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuditDAO extends BaseDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuditDAO.class);

	public int submitAudit(Audit audit) throws DatabaseException {
		try {
			return getSqlSession().insert("Audit.submitAudit", audit);
		} catch (Exception e) {
			LOGGER.error("DatabaseException : {}", e.getMessage());
			throw new DatabaseException(e.getMessage());
		}
	}
}