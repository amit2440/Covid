drop database covid;

CREATE SCHEMA IF NOT EXISTS `covid`;


CREATE TABLE IF NOT EXISTS `covid`.`user` (
    `user_id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `mobile` VARCHAR(10) NOT NULL,
    `role` VARCHAR(20) NOT NULL,
    `work_location` VARCHAR(20) NOT NULL,
    `token` VARCHAR(150) NOT NULL,
    `is_active` boolean NOT NULL DEFAULT 0,
	`mgr_id` INT NULL,
    PRIMARY KEY (`user_id`),
	INDEX `mrg_id_idx` (`mgr_id` ASC),
    UNIQUE INDEX `mobile_UNIQUE` (`mobile` ASC)
);


CREATE TABLE IF NOT EXISTS `covid`.`question` (
    `question_id` INT NOT NULL AUTO_INCREMENT,
    `question` VARCHAR(200) NOT NULL,
    `type` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`question_id`),
    UNIQUE INDEX `question_UNIQUE` (`question` ASC)
);

CREATE TABLE IF NOT EXISTS `covid`.`option` (
    `option_id` INT NOT NULL AUTO_INCREMENT,
    `display_name` VARCHAR(100) NULL,
	`checked` BIT NULL,
    `risk` VARCHAR(1) NOT NULL,
    PRIMARY KEY (`option_id`)
);

CREATE TABLE IF NOT EXISTS `covid`.`question_option` (
    `question_id` INT NOT NULL,
    `option_id` INT NOT NULL,
    INDEX `question_id_idx` (`question_id` ASC),
    INDEX `option_id_idx` (`option_id` ASC),
    CONSTRAINT `question_id` FOREIGN KEY (`question_id`)
        REFERENCES `covid`.`question` (`question_id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `option_id` FOREIGN KEY (`option_id`)
        REFERENCES `covid`.`option` (`option_id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);
	
CREATE TABLE IF NOT EXISTS `covid`.`survey` (
    `survey_id` INT NOT NULL AUTO_INCREMENT,
    `created` DATETIME NOT NULL,
    `description` VARCHAR(100) NULL,
    `is_active` BIT NULL DEFAULT 1,
    PRIMARY KEY (`survey_id`)
);

CREATE  TABLE `covid`.`survey_question` (
  `ssq_id` INT NOT NULL AUTO_INCREMENT ,
  `survey_id` INT NOT NULL ,
  `question_id` INT NOT NULL ,
  PRIMARY KEY (`ssq_id`) ,
  INDEX `ssq_survey_id_idx` (`survey_id` ASC) ,
  INDEX `ssq_question_id_idx` (`question_id` ASC) ,
  CONSTRAINT `ssq_survey_id`
    FOREIGN KEY (`survey_id` )
    REFERENCES `covid`.`survey` (`survey_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ssq_question_id`
    FOREIGN KEY (`question_id` )
    REFERENCES `covid`.`question` (`question_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE  TABLE `covid`.`feedback` (
  `user_id` INT NOT NULL,
  `ssq_id` INT NOT NULL ,
  `option_id` INT NOT NULL,
  INDEX `feeback_ssq_id_idx` (`ssq_id` ASC) ,
  INDEX `option_id_idx` (`option_id` ASC) ,
  CONSTRAINT `feeback_ssq_id`
    FOREIGN KEY (`ssq_id` )
    REFERENCES `covid`.`survey_question` (`ssq_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `feeback_option_id`
    FOREIGN KEY (`option_id` )
    REFERENCES `covid`.`option` (`option_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE `covid`.`location` (
  `location_id` int(11) NOT NULL AUTO_INCREMENT,
  `area` varchar(100) NOT NULL,
  `longitude` decimal(9,6) NOT NULL,
  `latitude` decimal(9,6) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`location_id`),
  KEY `user_Id_idx` (`user_id`),
  CONSTRAINT `user_Id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE  TABLE `covid`.`epass` (
  `user_id` INT NOT NULL ,
  `survey_id` INT NOT NULL ,
  `is_allowed` BIT NOT NULL ,
  `to_date` DATE NOT NULL ,
  `created_by` INT NOT NULL ,
  `created_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  INDEX `epass_user_id_idx` (`user_id` ASC),
  INDEX `epass_survey_id_idx` (`survey_id` ASC),
  CONSTRAINT `epass_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES `covid`.`user` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `epass_survey_id`
    FOREIGN KEY (`survey_id` )
    REFERENCES `covid`.`survey` (`survey_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `epass_created_by`
    FOREIGN KEY (`user_id` )
    REFERENCES `covid`.`user` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
ALTER TABLE `covid`.`epass`
  ADD COLUMN `from_Date` DATE ;

-- select * from user;
-- select * from feedback;
-- select * from survey_question;
-- select * from question_option;
-- select * from `option`;
-- select * from question;
-- select * from survey;

-- delete from feedback;
-- delete from survey_question;
-- delete from question_option;
-- delete from `option`;
-- delete from question;
-- delete from survey;

use covid;

insert into survey values (1, CURRENT_TIMESTAMP, '1st week of month', 1);

insert into question (question_id, question, `type`) values (1,'Please select your Gender','singleChoice');
insert into question (question_id, question, `type`) values (2,'Please select your Age-Group','singleChoice');
insert into question (question_id, question, `type`) values (3,'Select the Government Identified Zone for your Current Location','singleChoice');
insert into question (question_id, question, `type`) values (4,'Is your current location in the same district or city as your current work location?','singleChoice');
insert into question (question_id, question, `type`) values (5,'Have you travelled outside the district or city of your current location in last 14 days?','singleChoice');
insert into question (question_id, question, `type`) values (6,'How do you always commute to work?','singleChoice');
insert into question (question_id, question, `type`) values (7,'Do you have any of these pre-existing Health Conditions?','multiChoice');
insert into question (question_id, question, `type`) values (8,'Are you having one or more of the following Symptoms?','multiChoice');
insert into question (question_id, question, `type`) values (9,'Have you or someone you stay with, come in close contact with a Laboratory Confirmed COVID-19 Patient in the last 14 days?','singleChoice');
insert into question (question_id, question, `type`) values (10,'Have you taken a test for Corona? Please select the appropriate option','singleChoice');

insert into `option` (option_id, display_name, checked, risk) values(1,'Male',0,'L');
insert into `option` (option_id, display_name, checked, risk) values(2,'Female',0,'L');
insert into `option` (option_id, display_name, checked, risk) values(3,'Other',0,'L');

insert into `option` (option_id, display_name, checked, risk) values(4,'Below 30 Years',0,'L');	
insert into `option` (option_id, display_name, checked, risk) values(5,'Between 30 to 50 Years',0,'M');
insert into `option` (option_id, display_name, checked, risk) values(6,'Above 50 Years',0,'M');

insert into `option` (option_id, display_name, checked, risk) values(7,'Green Zone',0,'L');		
insert into `option` (option_id, display_name, checked, risk) values(8,'Orange Zone',0,'M');
insert into `option` (option_id, display_name, checked, risk) values(9,'Red Zone',0,'H');
insert into `option` (option_id, display_name, checked, risk) values(10,'Containment Zone',0,'H');
insert into `option` (option_id, display_name, checked, risk) values(11,'I don\'t for sure',0,'M');

insert into `option` (option_id, display_name, checked, risk) values(12,'Yes',0,'L');			
insert into `option` (option_id, display_name, checked, risk) values(13,'No',0,'H');

insert into `option` (option_id, display_name, checked, risk) values(14,'Yes',0,'H');			
insert into `option` (option_id, display_name, checked, risk) values(15,'No',0,'L');

insert into `option` (option_id, display_name, checked, risk) values(16,'Self Driven Vehicle - No car pooling',0,'L');	
insert into `option` (option_id, display_name, checked, risk) values(17,'Shared Vehicle Or Car Pooling',0,'H');
insert into `option` (option_id, display_name, checked, risk) values(18,'Company Provided Transport',0,'M');
insert into `option` (option_id, display_name, checked, risk) values(19,'Public Transport',0,'H');

insert into `option` (option_id, display_name, checked, risk) values(20,'No existing conditions',0,'L');		
insert into `option` (option_id, display_name, checked, risk) values(21,'Asthma',0,'M');
insert into `option` (option_id, display_name, checked, risk) values(22,'Cancer',0,'M');
insert into `option` (option_id, display_name, checked, risk) values(23,'Chronic Lung Disease (COPD)',0,'H');
insert into `option` (option_id, display_name, checked, risk) values(24,'Diabetes',0,'M');
insert into `option` (option_id, display_name, checked, risk) values(25,'Hypertension',0,'M');
insert into `option` (option_id, display_name, checked, risk) values(26,'Heart Disease',0,'M');

insert into `option` (option_id, display_name, checked, risk) values(27,'None',0,'L');                 	                                               
insert into `option` (option_id, display_name, checked, risk) values(28,'Fever',0,'M');
insert into `option` (option_id, display_name, checked, risk) values(29,'Dry Cough',0,'M');
insert into `option` (option_id, display_name, checked, risk) values(30,'Feeling shortness of breath',0,'H');
insert into `option` (option_id, display_name, checked, risk) values(31,'Sore throat',0,'H');
insert into `option` (option_id, display_name, checked, risk) values(32,'Hoarseness in voice',0,'M');
insert into `option` (option_id, display_name, checked, risk) values(33,'Headache',0,'M');
insert into `option` (option_id, display_name, checked, risk) values(34,'Running Nose',0,'M');

insert into `option` (option_id, display_name, checked, risk) values(35,'Yes, the test result was Positive',0,'H');	
insert into `option` (option_id, display_name, checked, risk) values(36,'Yes, the test result was Negative',0,'L');
insert into `option` (option_id, display_name, checked, risk) values(37,'No, I have not taken the test',0,'L');


insert into question_option (question_id, option_id) values(1,1);
insert into question_option (question_id, option_id) values(1,2);
insert into question_option (question_id, option_id) values(1,3);
insert into question_option (question_id, option_id) values(2,4);
insert into question_option (question_id, option_id) values(2,5);
insert into question_option (question_id, option_id) values(2,6);
insert into question_option (question_id, option_id) values(3,7);
insert into question_option (question_id, option_id) values(3,8);
insert into question_option (question_id, option_id) values(3,9);
insert into question_option (question_id, option_id) values(3,10);
insert into question_option (question_id, option_id) values(3,11);
insert into question_option (question_id, option_id) values(4,12);
insert into question_option (question_id, option_id) values(4,13);
insert into question_option (question_id, option_id) values(5,14);
insert into question_option (question_id, option_id) values(5,15);
insert into question_option (question_id, option_id) values(6,16);
insert into question_option (question_id, option_id) values(6,17);
insert into question_option (question_id, option_id) values(6,18);
insert into question_option (question_id, option_id) values(6,19);
insert into question_option (question_id, option_id) values(7,20);
insert into question_option (question_id, option_id) values(7,21);
insert into question_option (question_id, option_id) values(7,22);
insert into question_option (question_id, option_id) values(7,23);
insert into question_option (question_id, option_id) values(7,24);
insert into question_option (question_id, option_id) values(7,25);
insert into question_option (question_id, option_id) values(7,26);
insert into question_option (question_id, option_id) values(8,27);
insert into question_option (question_id, option_id) values(8,28);
insert into question_option (question_id, option_id) values(8,29);
insert into question_option (question_id, option_id) values(8,30);
insert into question_option (question_id, option_id) values(8,31);
insert into question_option (question_id, option_id) values(8,32);
insert into question_option (question_id, option_id) values(8,33);
insert into question_option (question_id, option_id) values(8,34);
insert into question_option (question_id, option_id) values(9,14);
insert into question_option (question_id, option_id) values(9,15);
insert into question_option (question_id, option_id) values(10,35);
insert into question_option (question_id, option_id) values(10,36);
insert into question_option (question_id, option_id) values(10,37);

insert into survey_question (ssq_id, survey_id, question_id) values(1,1,1);
insert into survey_question (ssq_id, survey_id, question_id) values(2,1,2);
insert into survey_question (ssq_id, survey_id, question_id) values(3,1,3);
insert into survey_question (ssq_id, survey_id, question_id) values(4,1,4);
insert into survey_question (ssq_id, survey_id, question_id) values(5,1,5);
insert into survey_question (ssq_id, survey_id, question_id) values(6,1,6);
insert into survey_question (ssq_id, survey_id, question_id) values(7,1,7);
insert into survey_question (ssq_id, survey_id, question_id) values(8,1,8);
insert into survey_question (ssq_id, survey_id, question_id) values(9,1,9);
insert into survey_question (ssq_id, survey_id, question_id) values(10,1,10);
