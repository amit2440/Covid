package com.med.disease.tracking.app.dao;

import com.med.disease.tracking.app.exception.DatabaseException;
import com.med.disease.tracking.app.model.Risk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class RiskDAO extends BaseDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(RiskDAO.class);

    public Optional<Risk> getRisk(Risk risk) throws DatabaseException {
        try{
            return Optional.ofNullable(getSqlSession().selectOne("Risk.getRisk", risk));
        } catch (Exception exception){
            LOGGER.error("Database Exception :{}", exception.getMessage());
            throw new DatabaseException(exception.getMessage());
        }
    }

    public Integer updateRiskStatus(Risk risk) throws DatabaseException{
        try {
            return getSqlSession().update("Risk.updateRisk", risk);
        } catch (Exception e) {
            LOGGER.error("DatabaseException : {}", e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
    }

    public Integer insertRisk(Risk risk) throws DatabaseException {
        try {
            return getSqlSession().insert("Risk.insertRisk", risk);
        } catch (Exception e) {
            LOGGER.error("DatabaseException : {}", e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
    }

    public List<Risk> getRisk(List<Risk> risks) throws DatabaseException {
        try{
            return getSqlSession().selectList("Risk.getRiskForUsers", risks);
        } catch (Exception exception){
            LOGGER.error("Database Exception :{}", exception.getMessage());
            throw new DatabaseException(exception.getMessage());
        }
    }
}
