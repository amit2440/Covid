package com.med.disease.tracking.app.controller;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.med.disease.tracking.app.dto.request.LocationRequestDTO;
import com.med.disease.tracking.app.handler.AddLocationHandler;
import com.med.disease.tracking.app.handler.FetchLocationHandler;

@RestController
public class LocationController {

    @Autowired
    private BeanFactory beanFactory;

    @GetMapping(value = "/users/{userId}/locations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserLocation(@ModelAttribute LocationRequestDTO locationRequestDTO,
                                             BindingResult bindingResult) throws Exception{
        return (ResponseEntity<?>) beanFactory.getBean(FetchLocationHandler.class)
                .handle(locationRequestDTO, bindingResult);
    }

    @PostMapping(value = "/users/locations", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addLocation(@RequestBody LocationRequestDTO locationRequestDTO,
                                            BindingResult bindingResult) throws Exception{
        return (ResponseEntity<?>) beanFactory.getBean(AddLocationHandler.class)
                .handle(locationRequestDTO, bindingResult);
    }
}
