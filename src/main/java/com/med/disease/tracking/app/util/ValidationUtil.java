package com.med.disease.tracking.app.util;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.AnswerRequestDTO;
import com.med.disease.tracking.app.dto.EPassRequestDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;

import java.time.LocalDate;
import java.util.List;

public class ValidationUtil {

	public static void validateFieldRequired(String errorField, Object value, Errors errors) {
		if (ObjectUtils.isEmpty(value)) {
			errors.rejectValue(errorField, "fieldName.required", new Object[] { errorField }, null);
		}
	}
	
	public static void validateBlankField(String errorField, Object value, Errors errors) {
		if (ObjectUtils.isEmpty(value) || StringUtils.isBlank(String.valueOf(value))) {
			errors.rejectValue(errorField, "fieldName.required", new Object[] { errorField }, null);
		}
	}
	
	public static void isUserEPassAllowed(String errorField, Object value, Errors errors) {
		EPassRequestDTO requestDTO = (EPassRequestDTO) value;
		if (requestDTO.getUserId() == null || requestDTO.getSurveyId() == null)
			return;

		Boolean isAllowed = null;
		try {
			isAllowed = BeanUtil.getBean(CommonUtil.class).isEPassApplicable(requestDTO.getUserId(),
					requestDTO.getSurveyId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (false == isAllowed) {
			errors.rejectValue(errorField, "epass.user.notallowed", new Object[] { errorField }, null);
		}
	}
	
	public static void validateToDate(String errorField, Object value, Errors errors) {
		if(ObjectUtils.isEmpty(value))
			return;
		
		LocalDate toDate = (LocalDate) value;
		if (toDate.isBefore(LocalDate.now())) {
			errors.rejectValue(errorField, "fieldValue.invalid", new Object[] { errorField }, null);
		}
	}

	
	public static void validateMobileNumber(String errorField, Object value, Errors errors) {
		if (!ObjectUtils.isEmpty(value)) {
			String regx = "(\\+91)?(-)?\\s*?(91)?\\s*?(\\d{3})-?\\s*?(\\d{3})-?\\s*?(\\d{4})";
			String mobileNumber = (String) value;
			if (!mobileNumber.matches(regx)) {
				errors.rejectValue(errorField, "mobile.number.invalid", new Object[] { errorField }, null);
			}
		}
	}

	public static void simpleStringValidation(String errorField, Object value, Errors errors) {
//		String regx = "^([a-zA-Z]{2,}\\\\s[a-zA-z]{1,}'?-?[a-zA-Z]{2,}\\\\s?([a-zA-Z]{1,})?)";
		String regx = "[a-zA-Z]{3,30}";
		if (!ObjectUtils.isEmpty(value)) {
			String mobileNumber = (String) value;
			if (!mobileNumber.matches(regx)) {
				errors.rejectValue(errorField, "only.string.allowed", new Object[] { errorField }, null);
			}
		}
	}

	public static void alphaNumericValidation(String errorField, Object value, Errors errors) {
		String regx = "^[a-zA-Z0-9_]*$";
		if (!ObjectUtils.isEmpty(value)) {
			String mobileNumber = (String) value;
			if (!mobileNumber.matches(regx)) {
				errors.rejectValue(errorField, "no.specialCharacter.validation", new Object[] { errorField }, null);
			}
		}
	}

	public static void relaxedString(String errorField, Object value, Errors errors) {
		String regx = "^[a-zA-Z0-9_\\s]*$";
		if (!ObjectUtils.isEmpty(value)) {
			String mobileNumber = (String) value;
			if (!mobileNumber.matches(regx)) {
				errors.rejectValue(errorField, "no.specialCharacter.validation", new Object[] { errorField }, null);
			}
		}
	}

	public static void validateAnswers(String errorField, Object value, Errors errors){
		List<AnswerRequestDTO> answerRequestDTOList = (List<AnswerRequestDTO>) value;
		for (int i =0; i<answerRequestDTOList.size();i++){
			String QUESTION_ID = "answers.["+i+"].questionId";
			validateBlankField(QUESTION_ID, answerRequestDTOList.get(i).getQuestionId(), errors);
		}
//		answerRequestDTOList.forEach(answerRequestDTO -> {
//			validateBlankField(Constant.Field.OPTIONS, answerRequestDTO.getOptionIds(), errors);
//		});
	}
	public static void main(String args[]) {
		
	}
}
