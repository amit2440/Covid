package com.med.disease.tracking.app.mapper;

import com.med.disease.tracking.app.dto.FeedbackDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.dto.response.FeedbackResponseDTO;
import com.med.disease.tracking.app.model.Feedback;
import com.med.disease.tracking.app.model.Survey;
import com.med.disease.tracking.app.model.SurveyQuestion;
import com.med.disease.tracking.app.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FetchFeedbackMapper extends Mapper{

    @Override
    protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
        FetchFeedbackRequestDTO fetchFeedbackRequestDTO = (FetchFeedbackRequestDTO)objectToMap;
        Feedback feedback = new Feedback();
        User user = new User();
        user.setUserId(fetchFeedbackRequestDTO.getUserId());
        Survey survey = new Survey();
        survey.setSurveyId(fetchFeedbackRequestDTO.getSurveyId());
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setSurvey(survey);
        feedback.setUser(user);
        feedback.setSurveyQuestion(surveyQuestion);
        return user;
    }

    @Override
    protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
        List<Feedback> feedbackList = (List) objectToMap;
        if(!feedbackList.isEmpty()){
            FeedbackDTO feedbackDTO = new FeedbackDTO();
            feedbackDTO.setUserId(feedbackList.stream().findFirst().get().getUser().getUserId());
            feedbackDTO.setSurveyId(feedbackList.stream().findFirst().get().getSurveyQuestion().getSurvey().getSurveyId());
            feedbackDTO.setFeedbacks(feedbackList.stream().map(feedback -> {
                FeedbackResponseDTO feedbackResponseDTO = new FeedbackResponseDTO();
                feedbackResponseDTO.setQuestion(feedback.getSurveyQuestion().getQuestion().getQuestion());
                feedbackResponseDTO.setRisk(feedback.getOption().getRisk());
                if(feedback.getOption().getType().equalsIgnoreCase("text"))
                    feedbackResponseDTO.setAnswer(feedback.getValue());
                else
                    feedbackResponseDTO.setAnswer(feedback.getOption().getDisplayName());
                return feedbackResponseDTO;
            }).collect(Collectors.toList()));
        }
        return null;
    }
}
