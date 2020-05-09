package com.med.disease.tracking.app.handler;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.LocationDTO;
import com.med.disease.tracking.app.dto.request.FetchLocationRequestDTO;
import com.med.disease.tracking.app.service.impl.LocationServiceImpl;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.FetchLocationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
public class FetchLocationHandler extends RestControllerHandler {

    private FetchLocationRequestDTO fetchLocationRequestDTO;

    @Autowired
    private LocationServiceImpl locationServiceImpl;

    @Override
    protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
        fetchLocationRequestDTO = (FetchLocationRequestDTO) request;
        this.bindingResult = result;
    }

    @Override
    protected void validateRequest() {
        new FetchLocationValidator().validate(fetchLocationRequestDTO, bindingResult);
        ErrorUtil.processError(bindingResult, Constant.Module.FETCH_LOCATION);
    }

    @Override
    protected Object processRequest() throws Exception {
        LocationDTO locationDTO = locationServiceImpl.fetchLocationForUser(fetchLocationRequestDTO);
        return ObjectUtils.isEmpty(locationDTO)
                ? new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.NOT_FOUND)
                : new ResponseEntity<LocationDTO>(locationDTO, HttpStatus.OK);
    }
}
