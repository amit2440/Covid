package com.med.disease.tracking.app.service;

import java.util.List;

import com.med.disease.tracking.app.dto.ManagerDTO;

public interface ManagerService {
	
	public int insertManager(ManagerDTO managerDTO) throws Exception;
	
	public List<ManagerDTO> getManagerList() throws Exception;
	
	public int updateUserManager(ManagerDTO managerDTO) throws Exception;
	
	public boolean validateData(ManagerDTO managerDTO) throws Exception;

}
