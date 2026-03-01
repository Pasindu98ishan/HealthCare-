package com.example.physiological.configuration.repository;

import com.example.physiological.configuration.entity.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, Long> {
    Optional<ConfigurationEntity> findByCustomer(String customer);
}