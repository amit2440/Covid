package com.med.disease.tracking.app.service.impl;

import com.med.disease.tracking.app.dao.FeedbackDAO;
import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.mapper.SubmitFeedbackMapper;
import com.med.disease.tracking.app.mapper.MappingTypeEnum;
import com.med.disease.tracking.app.model.Feedback;
import com.med.disease.tracking.app.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private SubmitFeedbackMapper submitFeedbackMapper;

    @Autowired
    private FeedbackDAO feedbackDAO;

    @Override
    @Transactional
    public void submitFeedback(FeedbackRequestDTO feedbackRequestDTO) throws Exception {
        Object object =  submitFeedbackMapper.map(feedbackRequestDTO, MappingTypeEnum.MAPTODOMAIN, null);
        List feedbackList = new ArrayList((Collection<?>) object);
        for(Object feedback : feedbackList){
            feedbackDAO.submitFeedback((Feedback) feedback);
        }
    }
}