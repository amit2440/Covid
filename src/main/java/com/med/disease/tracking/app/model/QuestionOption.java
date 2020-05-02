package com.med.disease.tracking.app.model;

public class QuestionOption {

    private Question question;
    private Option option;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "QuestionOption{" +
                "question=" + question +
                ", option=" + option +
                '}';
    }
}
