use covid;

/***** Insert User Record *****/
DROP PROCEDURE IF EXISTS InsertUser;
DELIMITER //
CREATE PROCEDURE InsertUser(firstname VARCHAR(50), lastname VARCHAR(50), mobile VARCHAR(10), 
role VARCHAR(10), location VARCHAR(50), otp VARCHAR(10), isactive BIT, managerid VARCHAR(50))
BEGIN
    INSERT INTO covid.user(
        first_name, last_name, mobile, role, work_location, token, is_active, mgr_id
    )
    VALUES(
        firstname, lastname, mobile, role, location, otp, isactive, managerid
    );
END //
DELIMITER ;

/***** Load Users *****/
DROP PROCEDURE IF EXISTS LoadTestUsers;
DELIMITER //
CREATE PROCEDURE LoadTestUsers(
    userCount INT,
    managerCount INT
)
BEGIN
    DECLARE counter INT DEFAULT 1;
    DECLARE firstname VARCHAR(50) DEFAULT "Test";
    DECLARE lastname VARCHAR(50) DEFAULT "User";
    DECLARE mobilenumber INT DEFAULT 1000000000;
    DECLARE mobile VARCHAR(10);
    DECLARE role VARCHAR(10) DEFAULT "ADMIN";
    DECLARE location VARCHAR(50) DEFAULT "Pune";
    DECLARE otp VARCHAR(10) DEFAULT "1234";
    DECLARE isactive BIT DEFAULT 1;
    DECLARE managerid VARCHAR(50) DEFAULT "1";

    WHILE counter <= userCount DO
		SET lastname = CONCAT("User ", counter);
        SET mobilenumber = 1000000000 + counter;
        SET mobile = CONVERT(mobilenumber, NCHAR(10));
        SET managerid = CONVERT(counter%managerCount + 1, NCHAR(50));

        CALL InsertUser(firstname, lastname, mobile, role, location, otp, isactive, managerid);
        SET counter = counter + 1;
        
    END WHILE;
END //
DELIMITER ;

# Invoke Load Users for 1500 records
CALL LoadTestUsers(1500, 50);