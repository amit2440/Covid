package com.med.disease.tracking.app.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.model.User;

@Component
public class UserInfoMapper extends Mapper{

	@Override
	protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
		// TODO Auto-generated method stub
		UserDTO userDTO = (UserDTO)objectToMap;
		User user = new User();
		user.setUserId(userDTO.getUserId());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setMobile(userDTO.getMobile());
		user.setWorkLocation(userDTO.getWorkLocation());
		user.setMgrID(userDTO.getMgrID());
		user.setRole(userDTO.getRole());
		return user;
	}

	@Override
    protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
        List<User> userList = (List) objectToMap;
        List<UserDTO> userDTOList = new ArrayList<UserDTO>();
        if(!userList.isEmpty()){
        	UserDTO userDTO = null;
        	
            for(User user:userList) {
            	userDTO = new UserDTO();
            	userDTO.setUserId(user.getUserId());
            	userDTO.setFirstName(user.getFirstName());
            	userDTO.setLastName(user.getLastName());
            	userDTO.setWorkLocation(user.getWorkLocation());
            	userDTO.setMobile(user.getMobile());
            	userDTO.setRole(user.getRole());
            	userDTO.setManagerName(user.getManagerName());
            	userDTO.setMgrID(user.getMgrID());
            	userDTOList.add(userDTO);
            }
        	
        	
        }  
        return userDTOList;
	}
}
