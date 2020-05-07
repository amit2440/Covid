package com.med.disease.tracking.app.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.exception.DatabaseException;
import com.med.disease.tracking.app.model.User;

@Repository
public class UserInfoDAO extends BaseDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoDAO.class);

	public List<User> searchUser(User user) throws Exception {
		try {
			return getSqlSession().selectList("UserInfo.searchUser", user);
		} catch (Exception exception) {
			LOGGER.error("Database Exception :{}", exception.getMessage());
			throw new DatabaseException(exception.getMessage());
		}
	}
	
	
	public int updateUser(User user) throws Exception {
		try {
			return getSqlSession().insert("UserInfo.updateUser", user);
		}catch (Exception exception) {
			LOGGER.error("Database Exception :{}", exception.getMessage());
			throw new DatabaseException(exception.getMessage());
		}
		
	}
	
	public List<User> searchUserByuserId(User user) throws Exception {
		try {
			return getSqlSession().selectList("UserInfo.searchUserByuserId", user);
		}catch (Exception exception) {
			LOGGER.error("Database Exception :{}", exception.getMessage());
			throw new DatabaseException(exception.getMessage());
		}
	}
}
