package com.med.disease.tracking.app.mapper;

import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SubmitFeedbackMapper extends Mapper {

    @Override
    protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
        FeedbackRequestDTO feedbackRequestDTO = (FeedbackRequestDTO) objectToMap;
        Feedback feedback = new Feedback();
        User user = new User();
        QuestionSet questionSet = new QuestionSet();
        user.setUid(feedbackRequestDTO.getUserId());
        questionSet.setSetId(feedbackRequestDTO.getQuestionSetId());
        feedback.setUser(user);
        feedback.setSet(questionSet);
        return feedbackRequestDTO.getAnswers().stream().map(answer -> {
            Question question = new Question();
            Option option = new Option();
            question.setQuestionId(answer.getQuestionId());
            feedback.setQuestion(question);
            option.setId(answer.getOptionId());
            feedback.setOption(option);
            feedback.setValue(answer.getValue());
            return feedback;
        }).collect(Collectors.toList());
    }

    @Override
    protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
        return null;
    }
}
