package com.example.physiological;

import com.example.physiological.database.MigrationRunner;
import javax.sql.DataSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ConfigurationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationServiceApplication.class, args);

    }

    @Bean
    public CommandLineRunner runMigrations(DataSource dataSource) {
        return args -> {
            new MigrationRunner(dataSource).runMigrations();
        };
    }

}