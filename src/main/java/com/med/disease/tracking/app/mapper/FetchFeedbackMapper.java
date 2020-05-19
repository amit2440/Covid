package com.med.disease.tracking.app.mapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.med.disease.tracking.app.dto.FeedbackDTO;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.dto.response.FeedbackResponseDTO;
import com.med.disease.tracking.app.model.Feedback;
import com.med.disease.tracking.app.model.Survey;
import com.med.disease.tracking.app.model.SurveyQuestion;
import com.med.disease.tracking.app.model.User;

@Component
public class FetchFeedbackMapper extends Mapper {

	@Override
	protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
		FetchFeedbackRequestDTO fetchFeedbackRequestDTO = (FetchFeedbackRequestDTO) objectToMap;
		Feedback feedback = new Feedback();
		User user = new User();
		user.setUserId(fetchFeedbackRequestDTO.getUserId());
		Survey survey = new Survey();
		survey.setSurveyId(fetchFeedbackRequestDTO.getSurveyId());
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		surveyQuestion.setSurvey(survey);
		feedback.setUser(user);
		feedback.setSurveyQuestion(surveyQuestion);
		return feedback;
	}

	@Override
	protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
		List<Feedback> feedbackList = (List) objectToMap;
		if (!ObjectUtils.isEmpty(feedbackList)) {
			FeedbackDTO feedbackDTO = new FeedbackDTO();
			User user = feedbackList.stream().findFirst().get().getUser();

			UserDTO userDTO = new UserDTO();
			userDTO.setUserId(user.getUserId());
			userDTO.setFirstName(user.getFirstName());
			userDTO.setLastName(user.getLastName());
			userDTO.setRole(user.getRole());
			userDTO.setWorkLocation(user.getWorkLocation());

			feedbackDTO.setFeedbacks(feedbackList.stream().map(feedback -> {
				FeedbackResponseDTO feedbackResponseDTO = new FeedbackResponseDTO();
				feedbackResponseDTO.setQuestion(feedback.getSurveyQuestion().getQuestion().getQuestion());
				feedbackResponseDTO.setRisk(feedback.getOption().getRisk());
				feedbackResponseDTO.setAnswer(feedback.getValue());
				return feedbackResponseDTO;
			}).collect(Collectors.toList()));
			return feedbackDTO;
		}
		return null;
	}
}
