package com.med.disease.tracking.app.mapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.med.disease.tracking.app.dto.FeedbackForSurveyDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.dto.response.FeedbackResponseDTO;
import com.med.disease.tracking.app.model.Feedback;
import com.med.disease.tracking.app.model.Survey;
import com.med.disease.tracking.app.model.SurveyQuestion;

@Component
public class FetchFeedbackForSurveyMapper extends Mapper {

    @Override
    protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
        FetchFeedbackRequestDTO fetchFeedbackRequestDTO = (FetchFeedbackRequestDTO) objectToMap;
        Feedback feedback = new Feedback();
        Survey survey = new Survey();
        survey.setSurveyId(fetchFeedbackRequestDTO.getSurveyId());
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setSurvey(survey);
        feedback.setSurveyQuestion(surveyQuestion);
        return feedback;
    }

    @Override
    protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
        List<Feedback> feedbackList = (List) objectToMap;
        if(!feedbackList.isEmpty()) {
            FeedbackForSurveyDTO feedbackForSurveyDTO = new FeedbackForSurveyDTO();
            feedbackForSurveyDTO.setSurveyId(feedbackList.stream().findFirst().get().getSurveyQuestion().getSurvey().getSurveyId());
            feedbackForSurveyDTO.setFeedbacks(feedbackList.stream().map(feedback -> {
                FeedbackResponseDTO feedbackResponseDTO = new FeedbackResponseDTO();
                feedbackResponseDTO.setQuestion(feedback.getSurveyQuestion().getQuestion().getQuestion());
                feedbackResponseDTO.setRisk(feedback.getOption().getRisk());
                feedbackResponseDTO.setAnswer(feedback.getValue());
                return feedbackResponseDTO;
            }).collect(Collectors.toList()));
            return feedbackForSurveyDTO;
        }
        return null;
    }
}
