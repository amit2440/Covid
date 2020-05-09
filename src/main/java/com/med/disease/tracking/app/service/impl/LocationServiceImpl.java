package com.med.disease.tracking.app.service.impl;

import com.med.disease.tracking.app.dao.LocationDAO;
import com.med.disease.tracking.app.dto.LocationDTO;
import com.med.disease.tracking.app.dto.request.AddLocationRequestDTO;
import com.med.disease.tracking.app.dto.request.FetchLocationRequestDTO;
import com.med.disease.tracking.app.exception.CovidAppException;
import com.med.disease.tracking.app.mapper.AddLocationMapper;
import com.med.disease.tracking.app.mapper.FetchLocationMapper;
import com.med.disease.tracking.app.mapper.MappingTypeEnum;
import com.med.disease.tracking.app.model.Location;
import com.med.disease.tracking.app.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationServiceImpl implements LocationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationServiceImpl.class);

    @Autowired
    private LocationDAO locationDAO;

    @Autowired
    private FetchLocationMapper fetchLocationMapper;

    @Autowired
    private AddLocationMapper addLocationMapper;

    @Override
    @Transactional(readOnly = true)
    public LocationDTO fetchLocationForUser(FetchLocationRequestDTO fetchLocationRequestDTO) throws Exception {
        Location location = (Location) fetchLocationMapper.map(fetchLocationRequestDTO, MappingTypeEnum.MAPTODOMAIN, null);
        Location locationForUser = locationDAO.fetchLocationForUser(location);
        return (LocationDTO) fetchLocationMapper.map(locationForUser, MappingTypeEnum.MAPTORESPONSE, null);
    }

    @Override
    @Transactional(readOnly = false)
    public void addLocationRequestDTO(AddLocationRequestDTO addLocationRequestDTO) throws Exception {
        Location location = (Location)addLocationMapper.map(addLocationRequestDTO, MappingTypeEnum.MAPTODOMAIN, null);
        if (locationDAO.addLocation(location) <= 0) {
            LOGGER.error("Unable to add location");
            throw new CovidAppException("Add location failed");
        }
    }
}
