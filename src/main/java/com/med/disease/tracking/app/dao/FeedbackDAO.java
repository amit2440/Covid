package com.med.disease.tracking.app.dao;

import com.med.disease.tracking.app.exception.DatabaseException;
import com.med.disease.tracking.app.model.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeedbackDAO extends BaseDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackDAO.class);

    public void submitFeedback(Feedback feedback) throws Exception{
        try {
            getSqlSession().insert("Feedback.insertFeedback", feedback);
        } catch(Exception exception){
            LOGGER.error("Database Exception :{}", exception.getMessage());
            throw new DatabaseException(exception.getMessage());
        }
    }
}
