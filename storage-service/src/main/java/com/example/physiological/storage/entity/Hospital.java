package com.example.physiological.storage.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "hospitals")
@Data
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;
}
