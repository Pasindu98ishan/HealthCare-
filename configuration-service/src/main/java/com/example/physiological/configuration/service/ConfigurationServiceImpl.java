package com.example.physiological.configuration.service;

import com.example.physiological.configuration.entity.ConfigurationEntity;
import com.example.physiological.configuration.model.ConfigurationData;
import com.example.physiological.configuration.repository.ConfigurationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConfigurationServiceImpl implements ConfigurationService {

    private final ConfigurationRepository configurationRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void updateConfiguration(String customer, ConfigurationData configurationData) {
        try {
            String configJson = objectMapper.writeValueAsString(configurationData);
            Optional<ConfigurationEntity> existingConfig = configurationRepository.findByCustomer(customer);

            if (existingConfig.isPresent()) {
                ConfigurationEntity config = existingConfig.get();
                config.setConfiguration(configJson);
                configurationRepository.save(config);
                log.info("Updated configuration for customer: {}", customer);
            } else {
                ConfigurationEntity newConfig = ConfigurationEntity.builder()
                        .customer(customer)
                        .configuration(configJson)
                        .build();
                configurationRepository.save(newConfig);
                log.info("Created new configuration for customer: {}", customer);
            }
        } catch (JsonProcessingException e) {
            log.error("Error processing configuration data for customer: {}", customer, e);
            throw new RuntimeException("Error processing configuration data", e);
        }
    }
}