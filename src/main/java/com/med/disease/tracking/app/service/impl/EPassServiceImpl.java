package com.med.disease.tracking.app.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.med.disease.tracking.app.dao.EPassDAO;
import com.med.disease.tracking.app.dto.EPassRequestDTO;
import com.med.disease.tracking.app.dto.EPassDTO;
import com.med.disease.tracking.app.exception.CovidAppException;
import com.med.disease.tracking.app.mapper.FetchEPassMapper;
import com.med.disease.tracking.app.mapper.MappingTypeEnum;
import com.med.disease.tracking.app.mapper.SubmitEPassMapper;
import com.med.disease.tracking.app.model.EPass;
import com.med.disease.tracking.app.service.EPassService;

@Service
public class EPassServiceImpl implements EPassService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EPassServiceImpl.class);

	@Autowired
	private SubmitEPassMapper submitEPassMapper;

	@Autowired
	private FetchEPassMapper fetchEPassMapper;

	@Autowired
	private EPassDAO ePassDAO;

	@Override
	@Transactional(readOnly = false)
	public void submitEPass(EPassRequestDTO requestDTO) throws Exception {
		EPass ePass = (EPass) submitEPassMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		if (ePassDAO.submitEPass(ePass) <= 0) {
			LOGGER.error("Unable to insert ePass for userId=" + requestDTO.getUserId());
			throw new CovidAppException("Insert EPass failed");
		}
	}

	@Override
	public EPassDTO fetchEPass(EPassRequestDTO requestDTO) throws Exception {
		EPass ePass = (EPass) fetchEPassMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		List<EPass> ePassUser = ePassDAO.searchUser(ePass);
		return (EPassDTO) fetchEPassMapper.map(ePassUser, MappingTypeEnum.MAPTORESPONSE, null);
	}
}
