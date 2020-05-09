package com.med.disease.tracking.app.handler;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.request.AddLocationRequestDTO;
import com.med.disease.tracking.app.service.impl.LocationServiceImpl;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.AddLocationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
public class AddLocationHandler extends RestControllerHandler{

    private AddLocationRequestDTO addLocationRequestDTO;

    @Autowired
    private LocationServiceImpl locationServiceImpl;

    @Override
    protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
        addLocationRequestDTO = (AddLocationRequestDTO) request;
        this.bindingResult = result;
    }

    @Override
    protected void validateRequest() {
        new AddLocationValidator().validate(addLocationRequestDTO, bindingResult);
        ErrorUtil.processError(bindingResult, Constant.Module.ADD_LOCATION);
    }

    @Override
    protected Object processRequest() throws Exception {
        locationServiceImpl.addLocationRequestDTO(addLocationRequestDTO);
        return new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.CREATED);
    }
}
