package com.med.disease.tracking.app.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.med.disease.tracking.app.exception.DatabaseException;
import com.med.disease.tracking.app.model.Feedback;
import com.med.disease.tracking.app.model.UserRisk;

@Repository
public class FeedbackDAO extends BaseDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackDAO.class);

    public int submitFeedback(Feedback feedback) throws Exception{
        try {
            return getSqlSession().insert("Feedback.insertFeedback", feedback);
        } catch(Exception exception){
            LOGGER.error("Database Exception :{}", exception.getMessage());
            throw new DatabaseException(exception.getMessage());
        }
    }

    public List<Feedback> getFeedbacks(Feedback feedback) throws Exception {
        try {
            return getSqlSession().selectList("Feedback.getUserSurveyFeedback", feedback);
        } catch (Exception exception){
            LOGGER.error("Database Exception :{}", exception.getMessage());
            throw new DatabaseException(exception.getMessage());
        }
    }

    public int deleteFeedback(Feedback feedback) throws Exception {
        try{
            return getSqlSession().delete("Feedback.deleteFeedback", feedback);
        } catch (Exception exception){
            LOGGER.error("Database Exception :{}", exception.getMessage());
            throw new DatabaseException(exception.getMessage());
        }
    }
    
    public List<UserRisk> getUserRisks(UserRisk userRisk) throws Exception {
        try {
            return getSqlSession().selectList("Feedback.getUserRisks", userRisk);
        } catch (Exception exception){
            LOGGER.error("Database Exception :{}", exception.getMessage());
            throw new DatabaseException(exception.getMessage());
        }
    }
    
    
}
