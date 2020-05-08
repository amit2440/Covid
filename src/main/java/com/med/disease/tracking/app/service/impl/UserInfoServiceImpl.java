package com.med.disease.tracking.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.med.disease.tracking.app.dao.UserInfoDAO;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.mapper.MappingTypeEnum;
import com.med.disease.tracking.app.mapper.UserInfoMapper;
import com.med.disease.tracking.app.model.User;
import com.med.disease.tracking.app.service.UserInfoService;
@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private UserInfoDAO userInfoDAO;
	
	@Autowired
	UserInfoMapper userInfoMapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserDTO> searchUser(UserDTO userDTO) throws Exception {
		// TODO Auto-generated method stub
		List<User> userList = null;
		User user = (User) userInfoMapper.map(userDTO, MappingTypeEnum.MAPTODOMAIN, null);
		if(user.getUserId()!=null) {
			userList = userInfoDAO.searchUserByuserId(user);
		}else {
			userList = userInfoDAO.searchUser(user);
		}
        return (List<UserDTO>) userInfoMapper.map(userList, MappingTypeEnum.MAPTORESPONSE, null);
	}
	
	@Override
	public boolean updateUser(UserDTO userDTO) throws Exception {
		User user = (User) userInfoMapper.map(userDTO, MappingTypeEnum.MAPTODOMAIN, null);
		return userInfoDAO.updateUser(user)>0?true:false;
	}
	
}
