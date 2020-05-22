package com.med.disease.tracking.app.mapper;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.model.User;

@Component
public class UserMapper extends Mapper{

	@Override
	protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
		User user = (User) objectToMap;
		UserDTO userDTO = new UserDTO();
		if (user != null) {
			userDTO.setUserId(user.getUserId());
			userDTO.setFirstName(user.getFirstName());
			userDTO.setLastName(user.getLastName());
			userDTO.setRole(user.getRole());
			userDTO.setWorkLocation(user.getWorkLocation());
		}
		return userDTO;
	}
}
