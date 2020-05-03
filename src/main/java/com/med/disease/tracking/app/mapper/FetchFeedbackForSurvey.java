package com.med.disease.tracking.app.mapper;

import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.model.Feedback;
import com.med.disease.tracking.app.model.Survey;
import com.med.disease.tracking.app.model.SurveyQuestion;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FetchFeedbackForSurvey extends Mapper {

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
        return null;
    }
}
