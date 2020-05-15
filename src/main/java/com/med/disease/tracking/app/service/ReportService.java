package com.med.disease.tracking.app.service;

import com.med.disease.tracking.app.dto.ReportRequestDTO;

public interface ReportService {

	public Object getFeedbackReportForManager(ReportRequestDTO reportRequestDTO) throws Exception;
	
}
