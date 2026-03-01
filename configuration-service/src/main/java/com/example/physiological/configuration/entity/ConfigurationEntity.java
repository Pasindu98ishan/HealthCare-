package com.example.physiological.configuration.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "configuration")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfigurationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String customer;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String configuration;
}