package com.med.disease.tracking.app.model;

public class Feedback {

    private User user;
    private QuestionSet set;
    private Question question;
    private Option option;
    private String value;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public QuestionSet getSet() {
        return set;
    }

    public void setSet(QuestionSet set) {
        this.set = set;
    }

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "user=" + user +
                ", set=" + set +
                ", question=" + question +
                ", option=" + option +
                ", value='" + value + '\'' +
                '}';
    }
}
