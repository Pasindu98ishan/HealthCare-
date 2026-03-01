package com.example.physiological.configuration.service;

import com.example.physiological.configuration.model.ConfigurationData;

public interface ConfigurationService {

    void updateConfiguration(String customer, ConfigurationData configurationData);
}
