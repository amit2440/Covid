CREATE SCHEMA IF NOT EXISTS `covid`;

CREATE TABLE IF NOT EXISTS `covid`.`user` (
    `user_id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `mobile` VARCHAR(10) NOT NULL,
    `role` VARCHAR(20) NOT NULL,
    `work_location` VARCHAR(20) NOT NULL,
    `token` VARCHAR(150) NOT NULL,
    `is_active` BINARY NOT NULL DEFAULT 1,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `mobile_UNIQUE` (`mobile` ASC),
    UNIQUE INDEX `token_UNIQUE` (`token` ASC)
);

CREATE TABLE IF NOT EXISTS `covid`.`question` (
    `question_id` INT NOT NULL AUTO_INCREMENT,
    `question` VARCHAR(100) NOT NULL,
    `control` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`question_id`),
    UNIQUE INDEX `question_UNIQUE` (`question` ASC)
);

CREATE TABLE IF NOT EXISTS `covid`.`option` (
    `option_id` INT NOT NULL AUTO_INCREMENT,
    `field_name` VARCHAR(20) NULL,
    `display_name` VARCHAR(20) NULL,
    `type` VARCHAR(20) NOT NULL,
    `size` INT NULL,
    `risk` INT NOT NULL DEFAULT 0,
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
	
CREATE TABLE IF NOT EXISTS `covid`.`question_set` (
    `set_id` INT NOT NULL AUTO_INCREMENT,
    `created` DATETIME NOT NULL,
    `description` VARCHAR(50) NULL,
    `is_active` BIT NULL DEFAULT 1,
    PRIMARY KEY (`set_id`)
);

CREATE TABLE IF NOT EXISTS `covid`.`feedback` (
    `user_id` INT NOT NULL,
    `set_id` INT NOT NULL,
    `question_id` INT NOT NULL,
    `option_id` INT NOT NULL,
    `value` VARCHAR(20) NOT NULL,
    INDEX `user_id_idx` (`user_id` ASC),
    INDEX `set_id_idx` (`set_id` ASC),
    INDEX `question_id_idx` (`question_id` ASC),
    INDEX `option_id_idx` (`option_id` ASC),
    CONSTRAINT `feedback_user_id` FOREIGN KEY (`user_id`)
        REFERENCES `covid`.`user` (`user_id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `feedback_set_id` FOREIGN KEY (`set_id`)
        REFERENCES `covid`.`question_set` (`set_id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `feedback_question_id` FOREIGN KEY (`question_id`)
        REFERENCES `covid`.`question` (`question_id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `feedback_option_id` FOREIGN KEY (`option_id`)
        REFERENCES `covid`.`option` (`option_id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);

