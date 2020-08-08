package com.med.disease.tracking.app.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.med.disease.tracking.app.dto.EPassDTO;
import com.med.disease.tracking.app.dto.EPassRequestDTO;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.model.EPass;
@Component
public class EpassReportMapper extends Mapper {

	@Override
	protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
		// TODO Auto-generated method stub
		EPassRequestDTO epassDTO = (EPassRequestDTO)objectToMap;
		EPass epass = new EPass();
		epass.setToDate(epassDTO.getToDate());
		epass.setLocation(epassDTO.getLocation());
		epass.setMgrId(epassDTO.getMgrId());
		return epass;
	}

	@Override
	protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
//		EPass ePass = (EPass) objectToMap;
		List<EPassDTO> ePassDTOList = new ArrayList<EPassDTO>();
		 List<EPass> ePassList = (List)  objectToMap;
		if(!ePassList.isEmpty()) {
			 for(EPass epass:ePassList) {
				 EPassDTO ePassDTO = new EPassDTO();
				 UserDTO user = new UserDTO();
				 user.setFirstName(epass.getUser().getFirstName());
				 user.setLastName(epass.getUser().getLastName());
				 user.setManagerName(epass.getUser().getManagerName());
				 ePassDTO.setUser(user);
				 ePassDTO.setIsAllowed(epass.getIsAllowed());
				 ePassDTOList.add(ePassDTO);
				 
			 }
		}
		return ePassDTOList;
	}
}
