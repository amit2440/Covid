package com.med.disease.tracking.app.service.impl;

import com.med.disease.tracking.app.dto.FeedbackDTO;
import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.mapper.FetchFeedbackMapper;
import com.med.disease.tracking.app.mapper.MappingTypeEnum;
import com.med.disease.tracking.app.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;

public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FetchFeedbackMapper fetchFeedbackMapper;

    @Autowired
    private FeedbackDAO feedbackDAO;

    @Override
    public void submitFeedback(FeedbackRequestDTO feedbackRequestDTO) throws Exception {
        FeedbackDTO feedbackDTO = (FeedbackDTO) fetchFeedbackMapper.map(feedbackRequestDTO, MappingTypeEnum.MAPTODOMAIN, null);
    }
}
