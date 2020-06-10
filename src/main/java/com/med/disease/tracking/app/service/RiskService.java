package com.med.disease.tracking.app.service;

import com.med.disease.tracking.app.dto.RiskRequestDTO;
import com.med.disease.tracking.app.dto.SurveyFeedbackDTO;
import com.med.disease.tracking.app.dto.SurveyReportDTO;
import com.med.disease.tracking.app.dto.request.FetchRiskRequestDTO;

public interface RiskService {
    void submitRisk(RiskRequestDTO riskRequestDTO) throws Exception;
    SurveyReportDTO fetchAllSurveyFeedbacks(FetchRiskRequestDTO requestDTO) throws Exception;
    SurveyFeedbackDTO fetchSurveyFeedbacks(FetchRiskRequestDTO requestDTO) throws Exception;
}
