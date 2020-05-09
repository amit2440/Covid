package com.med.disease.tracking.app.controller;

import com.med.disease.tracking.app.dto.request.AddLocationRequestDTO;
import com.med.disease.tracking.app.dto.request.FetchLocationRequestDTO;
import com.med.disease.tracking.app.handler.AddLocationHandler;
import com.med.disease.tracking.app.handler.FetchLocationHandler;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LocationController {

    @Autowired
    private BeanFactory beanFactory;

    @GetMapping(value = "/user/location/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserLocation(@ModelAttribute FetchLocationRequestDTO fetchLocationRequestDTO,
                                             BindingResult bindingResult) throws Exception{
        return (ResponseEntity<?>) beanFactory.getBean(FetchLocationHandler.class)
                .handle(fetchLocationRequestDTO, bindingResult);
    }

    @PostMapping(value = "/user/location", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addLocation(@RequestBody AddLocationRequestDTO addLocationRequestDTO,
                                            BindingResult bindingResult) throws Exception{
        return (ResponseEntity<?>) beanFactory.getBean(AddLocationHandler.class)
                .handle(addLocationRequestDTO, bindingResult);
    }
}
