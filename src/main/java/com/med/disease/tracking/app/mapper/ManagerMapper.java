package com.med.disease.tracking.app.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.med.disease.tracking.app.dto.ManagerDTO;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.model.Manager;
import com.med.disease.tracking.app.model.User;

@Component
public class ManagerMapper extends Mapper{

	@Override
	protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
		// TODO Auto-generated method stub
		ManagerDTO managerDTO = (ManagerDTO)objectToMap;
		Manager manager = new Manager();
		manager.setMgrFirstName(managerDTO.getMgrFirstName());
		manager.setMgrLastName(managerDTO.getMgrLastName());
		manager.setTeamName(managerDTO.getTeamName());
		manager.setMgrId(managerDTO.getMgrId());
		manager.setActive(managerDTO.isActive());
		
		return manager;
	}

	@Override
    protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
        List<Manager> managerList = (List) objectToMap;
        List<ManagerDTO> managerDTOList = new ArrayList<ManagerDTO>();
        if(!managerList.isEmpty()){
        	ManagerDTO managerDTO = null;
        	
            for(Manager mgr:managerList) {
            	managerDTO = new ManagerDTO();
            	managerDTO.setMgrId(mgr.getMgrId());
            	managerDTO.setMgrFirstName(mgr.getMgrFirstName());
            	managerDTO.setMgrLastName(mgr.getMgrLastName());
            	managerDTO.setTeamName(mgr.getTeamName());
            	managerDTOList.add(managerDTO);
            }
        	
        	
        }  
        return managerDTOList;
	}
}
