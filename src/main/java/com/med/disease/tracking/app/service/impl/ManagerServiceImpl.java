package com.med.disease.tracking.app.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.med.disease.tracking.app.dto.ManagerDTO;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.mapper.ManagerMapper;
import com.med.disease.tracking.app.mapper.MappingTypeEnum;
import com.med.disease.tracking.app.model.Manager;
import com.med.disease.tracking.app.repository.IManagerRepository;
import com.med.disease.tracking.app.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	IManagerRepository iManagerRepository;
	
	@Autowired
	ManagerMapper managerMapper;
	
	@Override
	public int insertManager(ManagerDTO managerDTO) throws Exception {
		// TODO Auto-generated method stub
//		Manager manager = (Manager)managerMapper.map(managerDTO, MappingTypeEnum.MAPTODOMAIN, null);
//		return iManagerRepository.insert(manager);
		return 0;
	}

	@Override
	public List<ManagerDTO> getManagerList() throws Exception {
		// TODO Auto-generated method stub
		List<Manager> managerList = iManagerRepository.findAll();
		return  (List<ManagerDTO>)managerMapper.map(managerList, MappingTypeEnum.MAPTORESPONSE, null);
	}

}
