package com.example.physiological.database;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class MigrationRunner {

    private static final Logger logger = LoggerFactory.getLogger(MigrationRunner.class);
    private final DataSource dataSource;

    public MigrationRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void runMigrations() {
        logger.info("Starting database migration...");
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .load();
        
        var result = flyway.migrate();
        logger.info("Database migration completed successfully. Applied {} migrations.", result.migrationsExecuted);
    }
}
