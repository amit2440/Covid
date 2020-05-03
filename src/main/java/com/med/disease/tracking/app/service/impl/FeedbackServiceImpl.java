package com.med.disease.tracking.app.service.impl;

import com.med.disease.tracking.app.dao.FeedbackDAO;
import com.med.disease.tracking.app.dto.FeedbackDTO;
import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.dto.request.UserRequestDTO;
import com.med.disease.tracking.app.dto.response.FeedbackResponseDTO;
import com.med.disease.tracking.app.exception.CovidAppException;
import com.med.disease.tracking.app.mapper.FetchFeedbackMapper;
import com.med.disease.tracking.app.mapper.Mapper;
import com.med.disease.tracking.app.mapper.SubmitFeedbackMapper;
import com.med.disease.tracking.app.mapper.MappingTypeEnum;
import com.med.disease.tracking.app.model.Feedback;
import com.med.disease.tracking.app.model.User;
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
    public FeedbackDTO fetchFeedbacks(UserRequestDTO userRequestDTO) throws Exception {
        User user = (User) fetchFeedbackMapper.map(userRequestDTO, MappingTypeEnum.MAPTODOMAIN, null);
        List<Feedback> feedbackList = feedbackDAO.getFeedbacks(user);
        FeedbackDTO feedbackDTO = (FeedbackDTO) fetchFeedbackMapper.map(feedbackList, MappingTypeEnum.MAPTORESPONSE, null);
        return feedbackDTO;
    }
}