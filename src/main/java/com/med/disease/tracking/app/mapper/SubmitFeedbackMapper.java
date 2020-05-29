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
        List<Feedback> feedbackList = new ArrayList<>();
        feedbackRequestDTO.getAnswers().forEach(answer -> answer.getOptionIds().forEach(optionId -> {
           Feedback feedback = new Feedback();
           User user = new User();
           Survey survey = new Survey();
           SurveyQuestion surveyQ = new SurveyQuestion();
           user.setUserId(feedbackRequestDTO.getUserId());
           survey.setSurveyId(feedbackRequestDTO.getSurveyId());
           feedback.setUser(user);
           surveyQ.setSurvey(survey);
           Question question = new Question();
           Option questionOption = new Option();
           question.setQuestionId(answer.getQuestionId());
           surveyQ.setQuestion(question);
           feedback.setSurveyQuestion(surveyQ);
           questionOption.setOptionId(optionId);
           feedback.setOption(questionOption);
           feedbackList.add(feedback);
        }));
        return feedbackList;
    }

    @Override
    protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
        return null;
    }
}
