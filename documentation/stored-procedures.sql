use covid;

/* DROP Unique index on Question Description */
ALTER TABLE question
  DROP INDEX question_UNIQUE;

/***** ADD NEW SURVEY *****/
DROP PROCEDURE IF EXISTS CREATE_SURVEY;
DELIMITER //
CREATE PROCEDURE CREATE_SURVEY(IN survey_name VARCHAR(500))
BEGIN

SET @survey_name = survey_name;

/* Get the latest survey id*/
SELECT survey_id INTO @existing_survey_id FROM survey order by survey_id desc Limit 1;
#SELECT @existing_survey_id;
/* Assign new survey id */
#SET @new_survey_id = @existing_survey_id + 1;
#SELECT @new_survey_id;
/* Inactivate existing surveys */
update covid.survey set is_active =0 where survey_id = @existing_survey_id;
/* Insert new survey */
#insert into covid.survey values (@new_survey_id, CURRENT_TIMESTAMP, @survey_name, 1);
insert into covid.survey(created, description, is_active) values (CURRENT_TIMESTAMP, @survey_name, 1);

END //
DELIMITER ;
/***** ADD NEW SURVEY ENDS *****/

/***** ADD NEW SURVEY QUESTION *****/
DROP PROCEDURE IF EXISTS CREATE_SURVEY_QUESTION;
DELIMITER //
CREATE PROCEDURE CREATE_SURVEY_QUESTION
(IN question_name VARCHAR(500),
IN question_type VARCHAR(500))
BEGIN
SET @question_name = question_name;
SET @question_type = question_type;

/* Get the latest question id*/
#SELECT question_id INTO @existing_question_id FROM question order by question_id desc Limit 1;
#SELECT @existing_question_id;

/* Assign new question id */
#SET @new_question_id = @existing_question_id + 1;

/* Insert new question */
insert into question (question, `type`) 
values (@question_name,@question_type);

/* Get the latest survey id*/
SELECT survey_id INTO @survey_id FROM survey order by survey_id desc Limit 1;

/* Get newly inserted question id */
SET @new_question_id = last_insert_id();
#SELECT @new_question_id; 

/* Get the latest survey id*/
insert into survey_question(ssq_id, survey_id, question_id) 
values(@new_question_id,@survey_id,@new_question_id);
END //
DELIMITER ;
/***** ADD NEW SURVEY QUESTION ENDS *****/

/***** ADD NEW SURVEY QUESTION OPTION *****/
DROP PROCEDURE IF EXISTS CREATE_SURVEY_QUESTION_OPTION;
DELIMITER //
CREATE PROCEDURE CREATE_SURVEY_QUESTION_OPTION
(IN option_display_name VARCHAR(500),
IN option_checked BIT,
IN option_risk VARCHAR(500))
BEGIN
SET @option_display_name = option_display_name;
SET @option_checked = option_checked;
SET @option_risk = option_risk;

/* Insert new option */
insert into covid.option (display_name, checked, risk) 
values (@option_display_name,@option_checked,@option_risk);

/* Get the latest question id*/
SELECT question_id INTO @question_id FROM question order by question_id desc Limit 1;
#select * from question order by question_id desc;

/* Get newly inserted option id */
SET @new_option_id = last_insert_id();
#SELECT @new_option_id;

/* Link Option with Question */
insert into question_option(question_id,option_id) 
values(@question_id,@new_option_id);
END //
DELIMITER ;
/***** ADD NEW SURVEY QUESTION ENDS *****/