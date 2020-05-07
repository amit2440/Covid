package com.med.disease.tracking.app.service;

import java.util.List;

import com.med.disease.tracking.app.dto.UserDTO;

public interface UserInfoService {
	public List<UserDTO> searchUser(UserDTO userDTO) throws Exception;
	public boolean updateUser(UserDTO userDTO) throws Exception;
}
