package com.med.disease.tracking.app.service;

import com.med.disease.tracking.app.dto.LocationDTO;
import com.med.disease.tracking.app.dto.request.LocationRequestDTO;

public interface LocationService {

    LocationDTO fetchLocation(LocationRequestDTO locationRequestDTO) throws Exception;
    void addLocation(LocationRequestDTO locationRequestDTO) throws Exception;
}
