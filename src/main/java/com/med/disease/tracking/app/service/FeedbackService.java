package com.med.disease.tracking.app.service;

import com.med.disease.tracking.app.dto.FeedbackDTO;
import com.med.disease.tracking.app.dto.FeedbackForSetDTO;
import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;

public interface FeedbackService {

    void submitFeedback(FeedbackRequestDTO feedbackRequestDTO) throws Exception;

    FeedbackDTO fetchFeedbacks(FetchFeedbackRequestDTO fetchFeedbackRequestDTO) throws Exception;

    FeedbackForSetDTO fetchFeedbacksForSurvey(FetchFeedbackRequestDTO fetchFeedbackRequestDTO) throws Exception;
}