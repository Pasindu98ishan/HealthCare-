package com.example.physiological.storage;

import com.example.physiological.database.MigrationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class StorageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorageServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner runMigrations(DataSource dataSource) {
        return args -> {
            new MigrationRunner(dataSource).runMigrations();
        };
    }
}