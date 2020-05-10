package com.med.disease.tracking.app.constant;

public interface Constant {

	public interface Message {
		String VALIDATION_FAILED = "validation.failed";
		String VALIDATION_DETAILS = "validation.failed.details";
	}

	public interface Module {
		String QUESTION_FETCH = "Fetch Question";
		String REGISTER_USER = "User Registration";
		String SUBMIT_FEEDBACK = "Submit Feedback";
		String FEEDBACK_FETCH = "Fetch Feedback";
		String USER_INFO = "User Information";
		String ADD_LOCATION = "Add Location";
		String FETCH_LOCATION = "Fetch Location";
	}

	public interface Field {
		String QUESTION_ID = "questionId";
		String QUESTION_IDS = "questionIds";
		String USER_NAME = "UserName";
		String UID = "uid";
		String ENABLED = "enabled";
		String MOBILE = "mobile";
		String FIRST_NAME = "firstName";
		String LAST_NAME = "lastName";
		String ROLE = "role";
		String MIDDLE_NAME = "middleName";
		String WORK_LOCATION = "workLocation";
		String SUBMIT_FEEDBACK = "Submit Feedback";
		String FEEDBACK_FETCH = "Fetch Feedback";
		String SURVEY_ID = "surveyId";
		String DESCRIPTION = "description";
		String ISACTIVE = "isActive";
		String USER_ID = "userId";
		String AREA = "area";
		String LONGITUDE = "longitude";
		String LATITUDE = "latitude";
		String QUESTION = "question";
		String TYPE = "type";
		String OPTIONS = "options";
	}
	public interface Database {
		String SCHEMA_DEFAULT = "covid";
		String TABLE_OPTION = "option";
		String TABLE_QUESTION = "question";
	}
}
