package com.med.disease.tracking.app.service;

import com.med.disease.tracking.app.dto.LocationDTO;
import com.med.disease.tracking.app.dto.request.AddLocationRequestDTO;
import com.med.disease.tracking.app.dto.request.FetchLocationRequestDTO;

public interface LocationService {

    LocationDTO fetchLocationForUser(FetchLocationRequestDTO fetchLocationRequestDTO) throws Exception;
    void addLocationRequestDTO(AddLocationRequestDTO addLocationRequestDTO) throws Exception;
}
