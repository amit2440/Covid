package com.med.disease.tracking.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.med.disease.tracking.app.dao.LocationDAO;
import com.med.disease.tracking.app.dto.LocationDTO;
import com.med.disease.tracking.app.dto.request.LocationRequestDTO;
import com.med.disease.tracking.app.exception.CovidAppException;
import com.med.disease.tracking.app.mapper.AddLocationMapper;
import com.med.disease.tracking.app.mapper.FetchLocationMapper;
import com.med.disease.tracking.app.mapper.MappingTypeEnum;
import com.med.disease.tracking.app.model.Location;
import com.med.disease.tracking.app.service.LocationService;

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
    @Transactional
    public LocationDTO fetchLocation(LocationRequestDTO locationRequestDTO) throws Exception {
        Location location = (Location) fetchLocationMapper.map(locationRequestDTO, MappingTypeEnum.MAPTODOMAIN, null);
        Location locationForUser = locationDAO.fetchLocationForUser(location);
        return (LocationDTO) fetchLocationMapper.map(locationForUser, MappingTypeEnum.MAPTORESPONSE, null);
    }

    @Override
    @Transactional(readOnly = false)
    public void addLocation(LocationRequestDTO locationRequestDTO) throws Exception {
        Location location = (Location)addLocationMapper.map(locationRequestDTO, MappingTypeEnum.MAPTODOMAIN, null);
        if (locationDAO.addLocation(location) <= 0) {
            LOGGER.error("Unable to add location");
            throw new CovidAppException("Add location failed");
        }
    }
}
