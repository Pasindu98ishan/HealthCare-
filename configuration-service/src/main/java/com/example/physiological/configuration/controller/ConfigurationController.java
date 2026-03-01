package com.example.physiological.configuration.controller;

import com.example.physiological.configuration.api.ConfigurationApi;
import com.example.physiological.configuration.model.ConfigurationData;
import com.example.physiological.configuration.service.ConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ConfigurationController implements ConfigurationApi {

    private final ConfigurationService configurationService;

    @Override
    public ResponseEntity<Void> updateConfiguration(String customer, ConfigurationData configurationData) {
        configurationService.updateConfiguration(customer, configurationData);
        return ResponseEntity.ok().build();
    }
}