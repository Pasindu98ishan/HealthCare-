package com.example.physiological.storage.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "wards")
@Data
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String alias;

    @Column(name = "hospital_id", nullable = false)
    private Long hospitalId;
}
