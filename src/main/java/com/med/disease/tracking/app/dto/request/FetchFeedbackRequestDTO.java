package com.med.disease.tracking.app.dto.request;

public class FetchFeedbackRequestDTO {

    private Integer userId;
    private Integer surveyId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }
}
