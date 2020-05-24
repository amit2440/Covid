package com.med.disease.tracking.app.dto;

import java.util.List;

public class AnswerRequestDTO {

    private Integer questionId;
    private List<Integer> optionIds;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public List<Integer> getOptionIds() {
        return optionIds;
    }

    public void setOptionIds(List<Integer> optionIds) {
        this.optionIds = optionIds;
    }
}
