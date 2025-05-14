# Resto Manager – Restaurant Management System

A restaurant management application built with **Java 17**, **JavaFX 13**, **Hibernate 6**, **Lombok**, and **Reflections**. The app follows a modular and maintainable architecture using an abstract `IJpaProvider` interface to decouple the JPA configuration from its implementation (Hibernate by default).

## Features

- **Restaurant Management**: Create, update, and delete restaurants.
- **Ticket System**: Assign digital queue tickets to customers on arrival.
- **Order Management**: Take orders, track statuses in real time, and serve dishes.
- **Resource Tracking**: Monitor availability of tables and plates.
- **Billing**: Generate bills and manage payment (cash, card, etc.).

## Technologies

- Java 17
- JavaFX 13 (no FXML)
- Hibernate ORM 6
- MySQL (default)
- PostgreSQL (optional)
- Maven
- Lombok
- Reflections

## Installation

1. **Clone the project**:
   ```bash
   git clone https://github.com/SolidCodeApp/java-tutorials.git
   cd restomanager
   ```

2. **Create the database**:
   - For **MySQL**:
     ```sql
     CREATE DATABASE restaurants_db;
     ```
     Use the `restaurants_db.sql` script located in `resources/sql/`.

   - For **PostgreSQL**:
     Use the `restaurants_db.pg.sql` script in the same folder.

3. **Configure Hibernate**:
   - Edit `src/main/resources/config/hibernate.config.properties`.
   - Comment/uncomment MySQL or PostgreSQL sections as needed.
   - Update database credentials if necessary.
   - In `pom.xml`, switch between MySQL and PostgreSQL dependencies.

4. **Run the project**:
   ```bash
   mvn clean install
   mvn exec:java -Dexec.mainClass="com.solid.App"
   ```

## Code Structure

The application uses a modular architecture inspired by extended MVC:

- `entities/`: JPA entities
- `dao/`: Data access layer (CRUD via Hibernate)
- `services/`: Business logic
- `managers/`: Use case coordination
- `controllers/`: JavaFX user interaction
- `models/`: UI view models
- `views/`: JavaFX UI in pure Java
- `configuration/`: JPA provider abstraction and Hibernate implementation

**Reflections** is used to dynamically detect certain components such as JPA entities.

## Tests

The project includes both unit and integration tests to ensure code quality and maintainability.

### Testing Tools

- **JUnit Jupiter (JUnit 5)**: For writing and running unit tests.
- **Mockito**: For mocking dependencies and isolating components.
- **JaCoCo**: For code coverage reports.

### Running Tests

- Run all tests:
  ```bash
  mvn test
  ```

- Generate coverage report:
  ```bash
  mvn clean test jacoco:report
  ```
  The report will be available at:
  ```
  target/site/jacoco/index.html
  ```

### Test Structure

Tests are organized to mirror the main codebase:

- `configuration/`: Unit tests for JPA setup (e.g. `HibernateProvider`)
- `dao/`: Integration tests for data access
- `services/`: Unit tests with mocked dependencies
- `managers/`: Use case coordination logic
- `controllers/`: (Optional) UI controller logic
- `models/`: (Optional) View model logic
- `utils/`: Utility classes

Each layer is tested either independently or in integration, depending on its role.  
Critical components like `HibernateProvider` are tested directly to ensure configuration correctness without relying solely on DAO integration tests.

## Notes

- The app uses **MySQL** by default. To switch to PostgreSQL, update the config files and dependencies accordingly.
- SQL setup scripts are located in `resources/sql/` for both MySQL and PostgreSQL.
- No heavy frameworks (e.g. Spring). Lifecycle and setup are manually handled.
- `IJpaProvider` allows flexible JPA configuration and easy replacement of Hibernate.
- JavaFX UI is written entirely in Java (no FXML) for full control.

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT). See the [LICENSE](./LICENSE) file for details.

## Author

Developed by **SolidCodeApp**  
A pedagogical Java project with a clear, modular, and maintainable architecture.
