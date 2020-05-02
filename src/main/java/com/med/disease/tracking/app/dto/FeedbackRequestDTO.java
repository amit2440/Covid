package com.med.disease.tracking.app.dto;

import java.util.List;

public class FeedbackRequestDTO {

    private Integer userId;
    private Integer questionSetId;
    private List<AnswerRequestDTO> answers;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuestionSetId() {
        return questionSetId;
    }

    public void setQuestionSetId(Integer questionSetId) {
        this.questionSetId = questionSetId;
    }

    public List<AnswerRequestDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerRequestDTO> answers) {
        this.answers = answers;
    }
}
