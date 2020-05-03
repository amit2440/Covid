package com.med.disease.tracking.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.med.disease.tracking.app.dto.response.FeedbackResponseDTO;

import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class FeedbackDTO {

    private Integer userId;
    private List<FeedbackResponseDTO> feedbacks;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<FeedbackResponseDTO> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackResponseDTO> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
