package com.med.disease.tracking.app.constant;

public interface Constant {
	
	public interface Message {
		String VALIDATION_FAILED = "validation.failed";
		String VALIDATION_DETAILS = "validation.failed.details";
	}
	public interface Module {
		String QUESTION_FETCH = "Fetch Question";
		String SUBMIT_FEEDBACK = "Submit Feedback";
		String FEEDBACK_FETCH = "Fetch Feedback";
	}
	public interface Field {
		String QUESTION_ID = "questionId";
		String SURVEY_ID = "surveyId";
		String DESCRIPTION = "description";
		String ISACTIVE = "isActive";
		String USER_ID = "userId";
	}
}
