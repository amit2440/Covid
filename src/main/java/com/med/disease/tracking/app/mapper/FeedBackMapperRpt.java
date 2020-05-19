package com.med.disease.tracking.app.mapper;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.med.disease.tracking.app.dto.ReportRequestDTO;
import com.med.disease.tracking.app.model.ReportRequest;

@Component
public class FeedBackMapperRpt extends Mapper{

	@Override
	protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
		// TODO Auto-generated method stub
		ReportRequestDTO reportRequestDTO = (ReportRequestDTO)objectToMap;
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setReportName(reportRequestDTO.getReportName());
		reportRequest.setMgrId(reportRequestDTO.getMgrId());
		
		return reportRequest;
	}

	@Override
    protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
//        List<Manager> managerList = (List) objectToMap;
//        List<ManagerDTO> managerDTOList = new ArrayList<ManagerDTO>();
//        if(!managerList.isEmpty()){
//        	ManagerDTO managerDTO = null;
//        	
//            for(Manager mgr:managerList) {
//            	managerDTO = new ManagerDTO();
//            	managerDTO.setMgrId(mgr.getMgrId());
//            	managerDTO.setMgrFirstName(mgr.getMgrFirstName());
//            	managerDTO.setMgrLastName(mgr.getMgrLastName());
//            	managerDTO.setTeamName(mgr.getTeamName());
//            	managerDTOList.add(managerDTO);
//            }
//        	
//        	
//        }  
        return null;
	}
}
