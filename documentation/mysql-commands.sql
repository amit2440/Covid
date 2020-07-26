drop database covid;

CREATE SCHEMA IF NOT EXISTS `covid`;

CREATE TABLE IF NOT EXISTS `covid`.`user` (
    `user_id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(60) NOT NULL,
    `last_name` VARCHAR(60) NOT NULL,
    `mobile_country_code` VARCHAR(10) NOT NULL DEFAULT '91',
    `mobile` VARCHAR(20) NOT NULL,
    `role` VARCHAR(20) NOT NULL,
    `work_location` VARCHAR(60) NOT NULL,
    `work_country` VARCHAR(60) NOT NULL DEFAULT 'India',
    `token` VARCHAR(150) NOT NULL,
    `is_active` boolean NOT NULL DEFAULT 0,
    `is_disclaimer_accepted` boolean NOT NULL DEFAULT 0,
	`mgr_id` INT NULL,
    PRIMARY KEY (`user_id`),
	INDEX `mrg_id_idx` (`mgr_id` ASC),
    UNIQUE INDEX `mobile_UNIQUE` (`mobile` ASC)
);


CREATE TABLE IF NOT EXISTS `covid`.`question` (
    `question_id` INT NOT NULL AUTO_INCREMENT,
    `question` VARCHAR(200) NOT NULL,
    `type` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`question_id`)
    #UNIQUE INDEX `question_UNIQUE` (`question` ASC)
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

CREATE TABLE IF NOT EXISTS `covid`.`survey_question` (
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

CREATE TABLE IF NOT EXISTS `covid`.`feedback` (
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

CREATE TABLE IF NOT EXISTS `covid`.`location` (
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

CREATE TABLE IF NOT EXISTS `covid`.`epass` (
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

CREATE TABLE IF NOT EXISTS `covid`.`risk` (
  `risk_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `survey_id` int(11) NOT NULL,
  `risk_level` varchar(45) NOT NULL,
  PRIMARY KEY (`risk_id`),
  KEY `fk_survey_id_idx` (`survey_id`),
  KEY `fk_user_id_idx` (`user_id`),
  CONSTRAINT `fkrisk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_survey_id` FOREIGN KEY (`survey_id`) REFERENCES `survey` (`survey_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

ALTER TABLE `covid`.`risk`
  ADD COLUMN `created_on` DATE ;
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
CREATE TABLE IF NOT EXISTS `covid`.`audit` (
  `user_id` INT NOT NULL ,
  `survey_id` INT NOT NULL ,
  `is_allowed` BIT NOT NULL ,
  `to_date` DATE NOT NULL ,
  `from_date` DATE NOT NULL ,
  `created_by` INT NOT NULL ,
  `created_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  INDEX `audit_user_id_idx` (`user_id` ASC),
  INDEX `audit_survey_id_idx` (`survey_id` ASC),
  CONSTRAINT `audit_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES `covid`.`user` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `audit_survey_id`
    FOREIGN KEY (`survey_id` )
    REFERENCES `covid`.`survey` (`survey_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `audit_created_by`
    FOREIGN KEY (`user_id` )
    REFERENCES `covid`.`user` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);