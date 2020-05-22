package com.med.disease.tracking.app.mapper;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.med.disease.tracking.app.dto.EPassDTO;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.model.EPass;

@Component
public class EPassMapper extends Mapper {

	@Override
	protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
		EPass ePass = (EPass) objectToMap;
		EPassDTO ePassDTO = new EPassDTO();
		if (ePass != null) {
			ePassDTO.setIsAllowed(ePass.getIsAllowed());
			ePassDTO.setToDate(ePass.getToDate());
			UserDTO createdBy = new UserDTO();
			if (ePass.getCreatedBy() != null) {
				createdBy.setUserId(ePass.getCreatedBy().getUserId());
				ePassDTO.setCreatedBy(createdBy);
			}
			ePassDTO.setCreatedOn(ePass.getCreatedOn());
		}
		return ePassDTO;
	}
}
