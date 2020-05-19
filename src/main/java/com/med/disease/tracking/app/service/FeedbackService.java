package com.med.disease.tracking.app.service;

import com.med.disease.tracking.app.dto.FeedbackDTO;
import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.dto.SurveyFeedbackDTO;
import com.med.disease.tracking.app.dto.SurveyReportDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;

public interface FeedbackService {

    void submitFeedback(FeedbackRequestDTO feedbackRequestDTO) throws Exception;

    FeedbackDTO fetchFeedbacks(FetchFeedbackRequestDTO fetchFeedbackRequestDTO) throws Exception;

    SurveyFeedbackDTO fetchSurveyFeedbacks(FetchFeedbackRequestDTO fetchFeedbackRequestDTO) throws Exception;
    
    SurveyReportDTO fetchAllSurveyFeedbacks(FetchFeedbackRequestDTO fetchFeedbackRequestDTO) throws Exception;
}
