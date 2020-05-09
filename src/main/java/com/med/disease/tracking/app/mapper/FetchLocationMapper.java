package com.med.disease.tracking.app.mapper;

import com.med.disease.tracking.app.dto.LocationDTO;
import com.med.disease.tracking.app.dto.request.FetchLocationRequestDTO;
import com.med.disease.tracking.app.model.Location;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Map;

@Component
public class FetchLocationMapper extends Mapper{

    @Override
    protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
        FetchLocationRequestDTO fetchLocationRequestDTO = (FetchLocationRequestDTO) objectToMap;
        Location location = new Location();
        location.setUserId(fetchLocationRequestDTO.getUserId());
        return location;
    }

    @Override
    protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
        if(ObjectUtils.isEmpty(objectToMap))
            return null;
        Location location = (Location) objectToMap;
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setArea(location.getArea());
        return locationDTO;
    }
}
