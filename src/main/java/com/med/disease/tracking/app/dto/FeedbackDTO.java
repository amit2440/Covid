package com.med.disease.tracking.app.dto;

public class FeedbackDTO {

    private UserDTO user;
    private QuestionSetDTO set;
    private QuestionDTO question;
    private OptionDTO option;
    private String value;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public QuestionSetDTO getSet() {
        return set;
    }

    public void setSet(QuestionSetDTO set) {
        this.set = set;
    }

    public QuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDTO question) {
        this.question = question;
    }

    public OptionDTO getOption() {
        return option;
    }

    public void setOption(OptionDTO option) {
        this.option = option;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
