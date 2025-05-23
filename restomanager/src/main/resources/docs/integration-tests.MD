# Integration Test Configuration

This document outlines the configuration and setup for running integration tests in the **Resto Manager** project. The project uses JavaFX with Java 17, and the frontend communicates directly with the backend via controllers.

## Environment Configuration

### **Database**
- **Database Type**: MySQL (Test Database)
- **Database URL**: `jdbc:mysql://localhost:3306/test_db`
- **Username**: `test_user`
- **Password**: `test_password`
- **Test Schema**: `test_schema`

### **Backend Configuration**
- **Controller Communication**: The JavaFX frontend communicates directly with the backend services through controllers.
- **JavaFX Application**: The backend logic is tied to JavaFX UI components (e.g., `Button`, `TextField`), where controller actions trigger backend logic (service and DAO layers).

### **Hibernate Configuration**
- **Configuration File**: `hibernate.config.test.properties`
- **Hibernate Dialect**: `org.hibernate.dialect.MySQL8Dialect`
- **JDBC Driver**: `com.mysql.cj.jdbc.Driver`
  
### **Flyway**
- **Flyway Version**: `5.x`
- **Migration Files Location**: `classpath:db/migration/test`
- **Flyway Configuration**:
  - Flyway automatically applies database migrations before running integration tests to ensure the database schema is up-to-date.

## CI/CD Integration

### **CI Pipeline**
- **Environment**: The integration tests are executed in a CI pipeline on each push to the `main` branch.
- **CI Tool**: Jenkins / GitLab CI / GitHub Actions (depending on the project's CI system)
- **Docker**: MySQL database is containerized for isolation during tests.
- **Commands**: The following Maven command is used to run integration tests:
  ```bash
  mvn clean test -Dtest=**/integration/**
