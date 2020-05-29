package com.med.disease.tracking.app.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.med.disease.tracking.app.dto.OptionDTO;

import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class FeedbackResponseDTO {
    private String question;
    private List<OptionDTO> answers;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<OptionDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<OptionDTO> answers) {
        this.answers = answers;
    }
}
