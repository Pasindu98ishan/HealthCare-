package com.example.physiological.storage.service.impl;

import com.example.physiological.storage.entity.Customer;
import com.example.physiological.storage.entity.Hospital;
import com.example.physiological.storage.entity.Ward;
import com.example.physiological.storage.model.CustomerRequest;
import com.example.physiological.storage.model.HospitalRequest;
import com.example.physiological.storage.model.StorageRequest;
import com.example.physiological.storage.model.WardRequest;
import com.example.physiological.storage.repository.CustomerRepository;
import com.example.physiological.storage.repository.HospitalRepository;
import com.example.physiological.storage.repository.WardRepository;
import com.example.physiological.storage.service.KafkaProducerService;
import com.example.physiological.storage.service.StorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final CustomerRepository customerRepository;
    private final HospitalRepository hospitalRepository;
    private final WardRepository wardRepository;
    private final KafkaProducerService kafkaProducerService;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void createCustomer(CustomerRequest request) {
        if (customerRepository.findByName(request.getName()).isPresent()) {
            // In a real app, you might throw an exception or return a specific status
            return;
        }
        Customer customer = new Customer();
        customer.setName(request.getName());
        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void createHospital(String customerName, HospitalRequest request) {
        Customer customer = customerRepository.findByName(customerName)
                .orElseThrow(() -> new RuntimeException("Customer not found: " + customerName));

        if (hospitalRepository.findByNameAndCustomerId(request.getName(), customer.getId()).isPresent()) {
            return;
        }

        Hospital hospital = new Hospital();
        hospital.setName(request.getName());
        hospital.setAddress(request.getAddress());
        hospital.setCustomerId(customer.getId());
        hospitalRepository.save(hospital);
    }

    @Override
    @Transactional
    public void createWard(String customerName, WardRequest request) {
        Customer customer = customerRepository.findByName(customerName)
                .orElseThrow(() -> new RuntimeException("Customer not found: " + customerName));

        Hospital hospital = hospitalRepository.findByNameAndCustomerId(request.getHospitalName(), customer.getId())
                .orElseThrow(() -> new RuntimeException("Hospital not found: " + request.getHospitalName()));

        if (wardRepository.findByNameAndHospitalId(request.getName(), hospital.getId()).isPresent()) {
            return;
        }

        Ward ward = new Ward();
        ward.setName(request.getName());
        ward.setAlias(request.getAlias());
        ward.setHospitalId(hospital.getId());
        wardRepository.save(ward);
    }

    @Override
    @Transactional
    public void storeData(String customerName, StorageRequest request) {
        Customer customer = customerRepository.findByName(customerName)
                .orElseThrow(() -> new RuntimeException("Customer not found: " + customerName));

        Hospital hospital = hospitalRepository.findByNameAndCustomerId(request.getHospital(), customer.getId())
                .orElseThrow(() -> new RuntimeException("Hospital not found: " + request.getHospital()));

        Ward ward = wardRepository.findByNameAndHospitalId(request.getWard(), hospital.getId())
                .orElseThrow(() -> new RuntimeException("Ward not found: " + request.getWard()));

        Map<String, Object> flatData = new HashMap<>();
        flatData.put("customer", customer.getName());
        flatData.put("hospital", hospital.getName());
        flatData.put("ward", ward.getName());
        if (request.getData() != null) {
            flatData.putAll(request.getData());
        }

        try {
            String message = objectMapper.writeValueAsString(flatData);
            kafkaProducerService.sendMessage(customer.getName(), message);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing data to JSON", e);
        }
    }
}
