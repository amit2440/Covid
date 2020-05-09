package com.med.disease.tracking.app.mapper;

import com.med.disease.tracking.app.dto.request.AddLocationRequestDTO;
import com.med.disease.tracking.app.dto.request.FetchLocationRequestDTO;
import com.med.disease.tracking.app.model.Location;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Component
public class AddLocationMapper extends Mapper{

    @Override
    protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
        AddLocationRequestDTO addLocationRequestDTO = (AddLocationRequestDTO) objectToMap;
        Location location = new Location();
        location.setUserId(addLocationRequestDTO.getUserId());
        location.setArea(addLocationRequestDTO.getArea());
        location.setDateCreated(LocalDate.now());
        location.setLatitude(addLocationRequestDTO.getLatitude());
        location.setLongitude(addLocationRequestDTO.getLongitude());
        return location;
    }

    @Override
    protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
        return null;
    }
}
