package com.med.disease.tracking.app.dao;

import com.med.disease.tracking.app.exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OptionDAO extends BaseDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(OptionDAO.class);

    public List<String> getOptionRisk(List<Integer> allOptionIds) throws DatabaseException {
        try {
            return getSqlSession().selectList("Option.optionRisk", allOptionIds);
        } catch (Exception exception){
            LOGGER.error("Database Exception :{}", exception.getMessage());
            throw new DatabaseException(exception.getMessage());
        }
    }
}
