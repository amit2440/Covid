package com.med.disease.tracking.app.service.impl;

import com.med.disease.tracking.app.dao.FeedbackDAO;
import com.med.disease.tracking.app.dto.FeedbackDTO;
import com.med.disease.tracking.app.dto.FeedbackForSetDTO;
import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.exception.CovidAppException;
import com.med.disease.tracking.app.mapper.FetchFeedbackForSurvey;
import com.med.disease.tracking.app.mapper.FetchFeedbackMapper;
import com.med.disease.tracking.app.mapper.SubmitFeedbackMapper;
import com.med.disease.tracking.app.mapper.MappingTypeEnum;
import com.med.disease.tracking.app.model.Feedback;
import com.med.disease.tracking.app.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private static Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    @Autowired
    private SubmitFeedbackMapper submitFeedbackMapper;

    @Autowired
    private FeedbackDAO feedbackDAO;

    @Autowired
    private FetchFeedbackMapper fetchFeedbackMapper;

    @Autowired
    private FetchFeedbackForSurvey fetchFeedbackForSurvey;

    @Override
    @Transactional
    public void submitFeedback(FeedbackRequestDTO feedbackRequestDTO) throws Exception {
        Object object =  submitFeedbackMapper.map(feedbackRequestDTO, MappingTypeEnum.MAPTODOMAIN, null);
        List feedbackList = new ArrayList((Collection<?>) object);
        for(Object feedback : feedbackList){
            if(feedbackDAO.submitFeedback((Feedback) feedback) <=0){
                LOGGER.error("Unable to submit Feedback");
                throw new CovidAppException("Submit feedback failed");
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public FeedbackDTO fetchFeedbacks(FetchFeedbackRequestDTO fetchFeedbackRequestDTO) throws Exception {
        Feedback feedback = (Feedback) fetchFeedbackMapper.map(fetchFeedbackRequestDTO, MappingTypeEnum.MAPTODOMAIN, null);
        List<Feedback> feedbackList = feedbackDAO.getFeedbacks(feedback);
        return (FeedbackDTO) fetchFeedbackMapper.map(feedbackList, MappingTypeEnum.MAPTORESPONSE, null);
    }

    @Override
    @Transactional(readOnly = true)
    public FeedbackForSetDTO fetchFeedbacksForSurvey(FetchFeedbackRequestDTO fetchFeedbackRequestDTO) throws Exception{
        Feedback feedback = (Feedback) fetchFeedbackForSurvey.map(fetchFeedbackRequestDTO, MappingTypeEnum.MAPTODOMAIN, null);
        List<Feedback> feedbackList = feedbackDAO.getFeedbacksForSurvey(feedback);
        return (FeedbackForSetDTO) fetchFeedbackForSurvey.map(feedbackList, MappingTypeEnum.MAPTORESPONSE, null);
    }
}