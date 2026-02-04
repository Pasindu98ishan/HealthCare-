package com.example.physiological.storage.model;

import com.example.physiological.storage.entity.Ward;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;


@Data
public class PhysiologicalData {

    private String customer;
    private String hospital;
    private String ward;
    private Map<String, String> data;
}
