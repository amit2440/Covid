package com.med.disease.tracking.app.mapper;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.model.Feedback;
import com.med.disease.tracking.app.model.Option;
import com.med.disease.tracking.app.model.Question;
import com.med.disease.tracking.app.model.Survey;
import com.med.disease.tracking.app.model.SurveyQuestion;
import com.med.disease.tracking.app.model.User;

@Component
public class SubmitFeedbackMapper extends Mapper {

    @Override
    protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
        FeedbackRequestDTO feedbackRequestDTO = (FeedbackRequestDTO) objectToMap;
        return feedbackRequestDTO.getAnswers().stream().map(answer -> {
            Feedback feedback = new Feedback();
            User user = new User();
            Survey survey = new Survey();
            SurveyQuestion surveyQ = new SurveyQuestion();
            user.setUserId(feedbackRequestDTO.getUserId());
            survey.setSurveyId(feedbackRequestDTO.getSurveyId());
            feedback.setUser(user);
            surveyQ.setSurvey(survey);
            Question question = new Question();
            Option option = new Option();
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
