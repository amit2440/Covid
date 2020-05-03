package com.med.disease.tracking.app.mapper;

import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.model.*;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SubmitFeedbackMapper extends Mapper {

    @Override
    protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
        FeedbackRequestDTO feedbackRequestDTO = (FeedbackRequestDTO) objectToMap;
        Feedback feedback = new Feedback();
        User user = new User();
        Survey survey = new Survey();
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        user.setUserId(feedbackRequestDTO.getUserId());
        survey.setSurveyId(feedbackRequestDTO.getSurveyId());
        feedback.setUser(user);
        surveyQuestion.setSurvey(survey);
        feedback.setSurveyQuestion(surveyQuestion);
        return feedbackRequestDTO.getAnswers().stream().map(answer -> {
            Question question = new Question();
            Option option = new Option();
            SurveyQuestion surveyQ = new SurveyQuestion();
            question.setQuestionId(answer.getQuestionId());
            surveyQ.setQuestion(question);
            feedback.setSurveyQuestion(surveyQ);
            option.setOptionId(answer.getOptionId());
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
