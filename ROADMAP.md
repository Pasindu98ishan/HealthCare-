# Project Roadmap

## Database Service Strategy

The `database-service` will serve as a **Shared Library** containing the Single Source of Truth for the database schema.

### 1. Structure & Responsibilities

*   **Location**: `database-service/`
*   **Type**: Maven Library Module (JAR) - *Not a standalone runnable service*.
*   **Key Components**:
    *   `src/main/resources/db/migration/`: Contains all versioned SQL scripts (e.g., `V1__init_schema.sql`).
    *   **Migration Utility**: A Java class (e.g., `DatabaseMigrationService`) that wraps Flyway logic.
    *   **Dependencies**: `flyway-core`, `spring-jdbc` (optional, if needed for context).

### 2. Workflow

#### A. Runtime (Deployment)
*   **Role**: The `database-service` acts strictly as a library.
*   **Execution**:
    *   Individual microservices (e.g., `storage-service`) include `database-service` as a dependency.
    *   When a service starts up, it invokes the migration method provided by `database-service`.
    *   This method scans for migration files (contained within the library) and applies any new changes to the central PostgreSQL database.
*   **Benefit**: Ensures that whichever service starts first (or all of them) can guarantee the database schema is up-to-date before processing data.

#### B. Build Time (The "Detection" Mechanism)
*   The `database-service` is packaged as a **JAR**.
*   Other services declare a dependency on `database-service`.
*   **Detection Process**:
    1.  Modify a script in `database-service`.
    2.  Build the project.
    3.  `storage-service` runs **Integration Tests** (using Testcontainers).
    4.  Test container applies scripts from the `database-service` JAR.
    5.  `storage-service` validates JPA Entities against this schema.
    6.  **Result**: Build fails if code doesn't match the schema.

### 3. Implementation Plan

**Step 1: Refactor `database-service`**
*   Convert `database-service` from a standalone Spring Boot app to a library module.
*   Remove `DatabaseServiceApplication.java` (main class).
*   Create `DatabaseMigrationRunner` class to handle Flyway execution.
*   Add `V1__initial_schema.sql`.

**Step 2: Configure `storage-service` (Consumer)**
*   Add `database-service` as a dependency in `pom.xml`.
*   Configure a `CommandLineRunner` or `@Bean` to trigger the migration on startup.
*   Set up Integration Tests.
