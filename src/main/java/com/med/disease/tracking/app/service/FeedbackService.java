package com.med.disease.tracking.app.service;

import com.med.disease.tracking.app.dto.FeedbackRequestDTO;

public interface FeedbackService {

    void submitFeedback(FeedbackRequestDTO feedbackRequestDTO) throws Exception;
}
