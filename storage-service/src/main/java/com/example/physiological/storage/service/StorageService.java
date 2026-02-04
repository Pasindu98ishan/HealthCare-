package com.example.physiological.storage.service;

import com.example.physiological.storage.model.CustomerRequest;
import com.example.physiological.storage.model.HospitalRequest;
import com.example.physiological.storage.model.StorageRequest;
import com.example.physiological.storage.model.WardRequest;

public interface StorageService {

    void createCustomer(CustomerRequest request);

    void createHospital(String customerName, HospitalRequest request);

    void createWard(String customerName, WardRequest request);

    void storeData(String customerName, StorageRequest request);
}
