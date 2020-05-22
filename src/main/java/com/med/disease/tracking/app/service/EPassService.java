package com.med.disease.tracking.app.service;

import com.med.disease.tracking.app.dto.EPassRequestDTO;
import com.med.disease.tracking.app.dto.EPassDTO;

public interface EPassService {
	void submitEPass(EPassRequestDTO requestDTO) throws Exception;
	EPassDTO fetchEPass(EPassRequestDTO requestDTO) throws Exception;
}
