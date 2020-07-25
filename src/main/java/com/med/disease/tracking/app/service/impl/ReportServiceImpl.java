package com.med.disease.tracking.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.med.disease.tracking.app.dao.EPassDAO;
import com.med.disease.tracking.app.dto.EPassRequestDTO;
import com.med.disease.tracking.app.dto.ReportRequestDTO;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.mapper.EpassReportMapper;
import com.med.disease.tracking.app.mapper.MappingTypeEnum;
import com.med.disease.tracking.app.model.EPass;
import com.med.disease.tracking.app.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	EpassReportMapper ePpassReportMapper;
	
	@Autowired
	EPassDAO epassDAO;
	
	@Override
	public Object getFeedbackReportForManager(ReportRequestDTO reportRequestDTO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Object allowedEpassReport(EPassRequestDTO reportRequestDTO) throws Exception {
		// TODO Auto-generated method stub
		EPass epass = (EPass) ePpassReportMapper.map(reportRequestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		List<EPass> epassList = epassDAO.allowedEpassReport(epass);
		 return (List<UserDTO>) ePpassReportMapper.map(epassList, MappingTypeEnum.MAPTORESPONSE, null);
	}

}
