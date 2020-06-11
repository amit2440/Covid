use covid;

CALL CREATE_SURVEY('11th June Covid Survey');

/* Question 1 */
CALL CREATE_SURVEY_QUESTION('Select the Government Identified Zone for your Current Location', 'singleChoice');
CALL CREATE_SURVEY_QUESTION_OPTION('Green Zone',0,'L');
CALL CREATE_SURVEY_QUESTION_OPTION('Orange Zone',0,'M');
CALL CREATE_SURVEY_QUESTION_OPTION('Red Zone',0,'M');
CALL CREATE_SURVEY_QUESTION_OPTION('Containment Zone',0,'H');
CALL CREATE_SURVEY_QUESTION_OPTION("I don't know for sure",0,'M');

/* Question 2 */
CALL CREATE_SURVEY_QUESTION('Is your Current location in the same District or City as your current Work Location?','singleChoice');
CALL CREATE_SURVEY_QUESTION_OPTION('Yes',0,'L');
CALL CREATE_SURVEY_QUESTION_OPTION('No',0,'H');

/* Question 3 */
CALL CREATE_SURVEY_QUESTION('Have you Travelled outside the District or City of your Current Location in last 14 Days?','singleChoice');
CALL CREATE_SURVEY_QUESTION_OPTION('Yes',0,'H');
CALL CREATE_SURVEY_QUESTION_OPTION('No',0,'L');

/* Question 4 */
CALL CREATE_SURVEY_QUESTION('Do you have any of these pre-existing Health Conditions?:Asthama, Cancer, Chronic Lung Disease (COPD), Diabetes, Hypertension OR Heart Disease','singleChoice');
CALL CREATE_SURVEY_QUESTION_OPTION('Yes',0,'H');
CALL CREATE_SURVEY_QUESTION_OPTION('No',0,'L');

/* Question 5 */
CALL CREATE_SURVEY_QUESTION('Are you experiencing one or more of the following Symptoms?','multiChoice');
CALL CREATE_SURVEY_QUESTION_OPTION('Fever',0,'H');
CALL CREATE_SURVEY_QUESTION_OPTION('Cold',0,'H');
CALL CREATE_SURVEY_QUESTION_OPTION('Running Nose',0,'H');
CALL CREATE_SURVEY_QUESTION_OPTION('Sore Throat',0,'H');
CALL CREATE_SURVEY_QUESTION_OPTION('Dry Cough',0,'H');
CALL CREATE_SURVEY_QUESTION_OPTION('Feeling shortness of breath',0,'H');
CALL CREATE_SURVEY_QUESTION_OPTION('Hoarseness in voice',0,'H');
CALL CREATE_SURVEY_QUESTION_OPTION('Loss of smell and taste',0,'H');

/* Question 6 */
CALL CREATE_SURVEY_QUESTION('Have you taken a Laboratory Test for COVID-2019? Please select the appropriate option','singleChoice');
CALL CREATE_SURVEY_QUESTION_OPTION('Yes, the test result was Positive',0,'H');
CALL CREATE_SURVEY_QUESTION_OPTION('Yes, the test result was Negative',0,'L');
CALL CREATE_SURVEY_QUESTION_OPTION('No, I have not taken the test',0,'L');

/* Question 7 */
CALL CREATE_SURVEY_QUESTION('Have you or someone you stay with, come in Close Contact with a Laboratory Confirmed COVID-2019 Patient in the last 14 days?','singleChoice');
CALL CREATE_SURVEY_QUESTION_OPTION('Yes',0,'H');
CALL CREATE_SURVEY_QUESTION_OPTION('No',0,'L');

/* Question 8 */
CALL CREATE_SURVEY_QUESTION('Have you or someone you stay with attended a large gathering or has been in instituional quarantine in the last 14 days?','singleChoice');
CALL CREATE_SURVEY_QUESTION_OPTION('Yes',0,'H');
CALL CREATE_SURVEY_QUESTION_OPTION('No',0,'L');

/* Question 9 */
CALL CREATE_SURVEY_QUESTION('If you are parent of creche-going children or are an expecting mother, please click Yes','singleChoice');
CALL CREATE_SURVEY_QUESTION_OPTION('Yes',0,'H');
CALL CREATE_SURVEY_QUESTION_OPTION('No',0,'L');

/* Question 10 */
CALL CREATE_SURVEY_QUESTION('Once you resume work from office, how will you Commute to Work?','singleChoice');
CALL CREATE_SURVEY_QUESTION_OPTION('Self Driven Vehicle - No Car Pooling',0,'L');
CALL CREATE_SURVEY_QUESTION_OPTION('Shared Vehicle OR Car Pooling',0,'H');
CALL CREATE_SURVEY_QUESTION_OPTION("Company Provided Transport (Applicable only if working in Shifts)",0,'M');
CALL CREATE_SURVEY_QUESTION_OPTION('Public Transport',0,'H');
