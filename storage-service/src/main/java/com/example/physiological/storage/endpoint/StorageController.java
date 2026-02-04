package com.example.physiological.storage.endpoint;

import com.example.physiological.storage.api.StorageApi;
import com.example.physiological.storage.model.CustomerRequest;
import com.example.physiological.storage.model.HospitalRequest;
import com.example.physiological.storage.model.StorageRequest;
import com.example.physiological.storage.model.WardRequest;
import com.example.physiological.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StorageController implements StorageApi {

    private final StorageService storageService;

    @Override
    public ResponseEntity<Void> storeData(String customer, StorageRequest storageRequest) {
        storageService.storeData(customer, storageRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> createCustomer(CustomerRequest customerRequest) {
        storageService.createCustomer(customerRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> createHospital(String customer, HospitalRequest hospitalRequest) {
        storageService.createHospital(customer, hospitalRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> createWard(String customer, WardRequest wardRequest) {
        storageService.createWard(customer, wardRequest);
        return ResponseEntity.ok().build();
    }
}
