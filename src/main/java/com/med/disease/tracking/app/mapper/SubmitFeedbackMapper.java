package com.med.disease.tracking.app.mapper;

import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FetchFeedbackMapper extends Mapper {

    @Override
    protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
        FeedbackRequestDTO feedbackRequestDTO = (FeedbackRequestDTO) objectToMap;
        Feedback feedback = new Feedback();
        User user = new User();
        user.setUid(feedbackRequestDTO.getUserId());
        QuestionSet questionSet = new QuestionSet();
        questionSet.setSetId(feedbackRequestDTO.getQuestionSetId());
        feedback.setUser(user);
        feedback.setSet(questionSet);
        return feedbackRequestDTO.getAnswers().stream().map(answer -> {
            Question question = new Question();
            question.setQuestionId(answer.getQuestionId());
            feedback.setQuestion(question);
            Option option = new Option();
            option.setId(answer.getOptionId());
            feedback.setValue(answer.getValue());
            return feedback;
        }).collect(Collectors.toList());
    }

    @Override
    protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
        return null;
    }
}
