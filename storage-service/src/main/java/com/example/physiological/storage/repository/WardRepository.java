package com.example.physiological.storage.repository;

import com.example.physiological.storage.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
    Optional<Ward> findByNameAndHospitalId(String name, Long hospitalId);
    Boolean existsByNameAndHospitalId(String name, Long hospitalId);
}
