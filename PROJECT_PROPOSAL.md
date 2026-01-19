# Microservices System Proposal: Physiological Data Processing Platform

## 1. High-Level System Architecture

This project proposes a scalable, event-driven microservices architecture designed to ingest, enrich, and aggregate physiological data in real-time. The system leverages **Java 21** and **Spring Boot** for service implementation, **Kafka** for asynchronous communication, and **Flink** & **Analytics** for stream processing.

A central **PostgreSQL** database serves as the primary system of record, with Change Data Capture (CDC) enabling real-time synchronization with the streaming pipeline.

### Core Components
*   **Storage Layer**: Centralized persistence.This will also  use to create APIs to create daa, customers patients departments etc
*   **Streaming Layer**: Kafka backbone for event propagation.
*   **Processing Layer**: Flink for complex enrichment and Analytics for aggregation.
*   **Presentation Layer**: APIs for consuming processed insights.
*   **Database Management**: Centralized schema management and migration.

---

## 2. Microservices Responsibilities

### 1. Storage Service
*   **Role**: The entry point for raw physiological data.
*   **Responsibilities**:
    *   Expose REST endpoints (POST /readings) to receive data (Heart Rate, BP, etc.).
    *   Persist raw data immediately to the central **PostgreSQL** database.
    *   Publish an "Ingested" event to a Kafka topic (`raw-readings-topic`) for downstream processing.
    *   This will also  use to create APIs to create daa, customers patients departments etc

### 2. Enricher Service (Flink Processor)
*   **Role**: The intelligence engine for data augmentation.
*   **Responsibilities**:
    *   Consume raw data from `raw-readings-topic`.
    *   Listen to CDC events (via Debezium/Kafka Connect) from PostgreSQL to detect changes in reference data (e.g., patient metadata updates).
    *   Execute **Apache Flink** jobs to join raw streams with static/reference data.
    *   Publish enriched data to `enriched-readings-topic`.

### 3. Analytics Service (Aggregator)
*   **Role**: Real-time analytics and query engine.
*   **Responsibilities**:
    *   Consume from `enriched-readings-topic`.
    *   Use **Analytics** to perform windowed aggregations (e.g., "Average Heart Rate per 5 minutes").
    *   Expose the materialized views or results via REST APIs or WebSockets for the Frontend.

### 4. Configuration Service
*   **Role**: Management of dynamic system and customer settings.
*   **Responsibilities**:
    *   Store and manage customer-specific configurations (e.g., alert thresholds, device settings).
    *   Provide APIs for other services to fetch current configurations.
    *   (Optional) Push configuration updates to Kafka to allow services to react dynamically.

### 5. Database Service
*   **Role**: Database schema management and maintenance.
*   **Responsibilities**:
    *   Manage database migrations using **Flyway**.
    *   Execute database initialization scripts.
    *   Handle other database-related maintenance tasks.
    *   Ensure schema consistency across environments.

---

## 3. Inter-Service Communication Flow

1.  **Ingestion**: External Device -> HTTP POST -> **Storage Service**.
2.  **Persistence**: Storage Service -> INSERT -> **PostgreSQL**.
3.  **Propagation**: Storage Service -> Produce -> **Kafka** (`raw-readings`).
4.  **CDC (Parallel)**: PostgreSQL -> Debezium -> **Kafka** (`db-changes`).
5.  **Enrichment**:
    *   **Enricher Service** (Flink) consumes `raw-readings` and `db-changes`.
    *   Processing: Join streams, validate data against rules.
    *   Output -> **Kafka** (`enriched-readings`).
6.  **Aggregation**:
    *   **Analytics Service** consumes `enriched-readings`.
    *   Processing: `SELECT avg(heart_rate) FROM stream WINDOW TUMBLING (SIZE 5 MINUTES)...`
7.  **Consumption**: Frontend -> HTTP GET / WebSocket -> **Analytics Service**.

---

## 4. Maven Multi-Module Layout & Folder Structure

We will use a single Git repository with a Maven multi-module build to manage dependencies and build processes efficiently.

### Directory Structure

```text
physiological-platform/
├── pom.xml                     # Root POM (Parent)
├── common-lib/                 # Shared DTOs, Utils, Constants
│   ├── pom.xml
│   └── src/main/java/...
├── storage-service/            # Spring Boot App
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/main/java/...
├── enricher-service/           # Flink Job / Spring Boot Wrapper
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/main/java/...
├── analytics-service/               # Analytics Client / API Wrapper
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/main/java/...
├── config-service/             # Spring Boot App
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/main/java/...
├── database-service/           # Flyway & DB Maintenance
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/main/java/...
└── docker/                     # Infrastructure
    ├── docker-compose.yml
    ├── postgres/
    │   └── init.sql
    └── analytics/
        └── queries.sql
```

### Root POM Configuration
*   **Packaging**: `pom`
*   **Java Version**: 21
*   **Spring Boot Version**: 3.2.x (Note: Spring Boot 4 is not yet released; 3.x is the current stable version supporting Java 21).
*   **Dependency Management**: Centralized version control for Spring Cloud, Kafka clients, and Flink libraries.

---

## 5. Technology Justification

*   **Java 21**: LTS release providing Virtual Threads (Project Loom), which significantly improves throughput for I/O-heavy microservices (like the Storage Service).
*   **Spring Boot 3**: Native support for Java 17+, improved observability with Micrometer, and robust Kafka integration.
*   **Kafka**: The industry standard for high-throughput, fault-tolerant event streaming. Decouples the ingestion from processing.
*   **Flink**: Chosen over simple Kafka Streams for the Enricher Service due to its advanced state management, ability to handle complex joins (CDC + Stream), and exactly-once processing guarantees.
*   **PostgreSQL**: Robust relational database. Using a central DB simplifies the initial data model, though strict schema separation per service is usually preferred in pure microservices (mitigated here by CDC).
*   **Analytics**: Allows writing SQL-like queries on streams, reducing the code required for aggregations and making analytics accessible via REST.
*   **Flyway**: Version control for database schemas, ensuring reliable and reproducible database migrations.

---

## 6. Scalability & Fault Tolerance Recommendations

### Scalability
*   **Stateless Services (Storage, Config)**: Can be horizontally scaled (multiple replicas) behind a Load Balancer.
*   **Kafka**: Partition topics based on `patient_id` or `device_id` to ensure ordering and allow parallel consumption by consumer groups.
*   **Flink**: Scale parallelism to match Kafka partitions. Use RocksDB state backend for large state handling.

### Fault Tolerance
*   **Retry Mechanisms**: Implement exponential backoff for DB connections and Kafka publishing.
*   **Dead Letter Queues (DLQ)**: Configure Kafka consumers to send malformed or unprocessable messages to a DLQ to prevent stream blocking.
*   **Circuit Breakers**: Use Resilience4j in the Storage Service when calling the DB or Kafka to fail fast during outages.
*   **Checkpointing**: Enable Flink checkpointing to ensure state recovery in case of pod failure.
