package com.med.disease.tracking.app.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.annotation.RequestScope;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.LocationDTO;
import com.med.disease.tracking.app.dto.request.LocationRequestDTO;
import com.med.disease.tracking.app.service.LocationService;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.FetchLocationValidator;

@RequestScope
@Component
public class FetchLocationHandler extends RestControllerHandler {

    private LocationRequestDTO locationRequestDTO;

    @Autowired
    private LocationService locationService;

    @Override
    protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
        locationRequestDTO = (LocationRequestDTO) request;
        this.bindingResult = result;
    }

    @Override
    protected void validateRequest() {
        new FetchLocationValidator().validate(locationRequestDTO, bindingResult);
        ErrorUtil.processError(bindingResult, Constant.Module.FETCH_LOCATION);
    }

    @Override
    protected Object processRequest() throws Exception {
        LocationDTO locationDTO = locationService.fetchLocation(locationRequestDTO);
        return ObjectUtils.isEmpty(locationDTO)
                ? new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.NOT_FOUND)
                : new ResponseEntity<LocationDTO>(locationDTO, HttpStatus.OK);
    }
}
