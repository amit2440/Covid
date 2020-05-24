package com.med.disease.tracking.app.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.dto.OptionDTO;
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

				Map<String, List<OptionDTO>> questionAnswers = new HashMap<>();

			feedbackList.forEach(feedback -> {
					OptionDTO answer = new OptionDTO();
					answer.setChecked(feedback.getOption().getChecked());
					answer.setDisplayName(feedback.getOption().getDisplayName());
					answer.setRisk(feedback.getOption().getRisk());
					answer.setOptionId(feedback.getOption().getOptionId());
					if (ObjectUtils.isEmpty(questionAnswers.get(feedback.getSurveyQuestion().getQuestion().getQuestion()))) {
						List<OptionDTO> ans = new ArrayList<>();
						ans.add(answer);
						questionAnswers.put(feedback.getSurveyQuestion().getQuestion().getQuestion(), ans);
					} else {
						questionAnswers.get(feedback.getSurveyQuestion().getQuestion().getQuestion()).add(answer);
					}
				});
				List<FeedbackResponseDTO> feedbackResponseDTOList = new ArrayList<>();
				for(Map.Entry<String, List<OptionDTO>> entry : questionAnswers.entrySet()) {
					FeedbackResponseDTO feedbackResponseDTO = new FeedbackResponseDTO();
					feedbackResponseDTO.setQuestion(entry.getKey());
					feedbackResponseDTO.setAnswers(entry.getValue());
					feedbackResponseDTOList.add(feedbackResponseDTO);
				}
			feedbackDTO.setFeedbacks(feedbackResponseDTOList);
			return feedbackDTO;
		}
		return null;
	}
}
