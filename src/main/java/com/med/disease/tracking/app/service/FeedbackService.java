package com.med.disease.tracking.app.service;

import com.med.disease.tracking.app.dto.FeedbackDTO;
import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.dto.request.UserRequestDTO;
import com.med.disease.tracking.app.model.Feedback;

import java.util.List;

public interface FeedbackService {

    void submitFeedback(FeedbackRequestDTO feedbackRequestDTO) throws Exception;

    FeedbackDTO fetchFeedbacks(UserRequestDTO userRequestDTO) throws Exception;
}
