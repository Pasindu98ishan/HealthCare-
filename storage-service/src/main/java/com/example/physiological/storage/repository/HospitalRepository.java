package com.example.physiological.storage.repository;

import com.example.physiological.storage.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    Optional<Hospital> findByNameAndCustomerId(String name, Long customerId);
}
