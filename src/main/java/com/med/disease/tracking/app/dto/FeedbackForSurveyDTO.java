package com.med.disease.tracking.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.med.disease.tracking.app.dto.response.FeedbackResponseDTO;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class FeedbackForSurveyDTO {

    private Integer surveyId;
    private List<FeedbackResponseDTO> feedbacks;

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public List<FeedbackResponseDTO> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackResponseDTO> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
