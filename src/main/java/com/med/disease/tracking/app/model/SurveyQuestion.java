package com.med.disease.tracking.app.model;

public class SurveyQuestion {

    private Integer ssqId;
    private Survey survey;
    private Question question;

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getSsqId() {
        return ssqId;
    }

    public void setSsqId(Integer ssqId) {
        this.ssqId = ssqId;
    }
}
