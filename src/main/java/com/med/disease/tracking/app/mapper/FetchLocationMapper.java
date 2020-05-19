package com.med.disease.tracking.app.mapper;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.med.disease.tracking.app.dto.LocationDTO;
import com.med.disease.tracking.app.dto.request.LocationRequestDTO;
import com.med.disease.tracking.app.model.Location;

@Component
public class FetchLocationMapper extends Mapper{

    @Override
    protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
        LocationRequestDTO locationRequestDTO = (LocationRequestDTO) objectToMap;
        Location location = new Location();
        location.setUserId(locationRequestDTO.getUserId());
        return location;
    }

    @Override
    protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
        if(ObjectUtils.isEmpty(objectToMap))
            return null;
        Location location = (Location) objectToMap;
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setArea(location.getArea());
        locationDTO.setLatitude(location.getLatitude());
        locationDTO.setLongitude(location.getLongitude());
        return locationDTO;
    }
}
