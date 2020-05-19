package com.med.disease.tracking.app.mapper;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.med.disease.tracking.app.dto.request.LocationRequestDTO;
import com.med.disease.tracking.app.model.Location;

@Component
public class AddLocationMapper extends Mapper{

    @Override
    protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
        LocationRequestDTO locationRequestDTO = (LocationRequestDTO) objectToMap;
        Location location = new Location();
        location.setUserId(locationRequestDTO.getUserId());
        location.setArea(locationRequestDTO.getArea());
        location.setCreated(LocalDateTime.now());
        location.setLatitude(locationRequestDTO.getLatitude());
        location.setLongitude(locationRequestDTO.getLongitude());
        return location;
    }

    @Override
    protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
        return null;
    }
}
