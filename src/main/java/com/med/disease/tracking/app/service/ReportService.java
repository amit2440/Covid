package com.med.disease.tracking.app.service;

import com.med.disease.tracking.app.dto.EPassRequestDTO;
import com.med.disease.tracking.app.dto.ReportRequestDTO;

public interface ReportService {

	public Object getFeedbackReportForManager(ReportRequestDTO reportRequestDTO) throws Exception;
	
	public Object allowedEpassReport(EPassRequestDTO reportRequestDTO) throws Exception;
	
	public Integer countAllowedEpass(EPassRequestDTO reportRequestDTO) throws Exception;
	
}
