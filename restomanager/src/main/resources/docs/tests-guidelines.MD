# Purpose of Tests

This document defines the testing practices applied in the Resto Manager project.  
It is intended for contributors to maintain consistent, testable, and high-quality code.

Tests are designed to validate the behavior of each class independently,  
ensuring that each component works correctly, with or without interaction with other external systems.

---

# Test Structure

Tests are organized into two main categories:
- `test/unit`: for unit tests (Mockito, JUnit)
- `test/integration`: for integration tests (real database, H2 or MySQL)

Each class should have a corresponding test class with the `Test` suffix.

Example:  
    `com.solid.configuration.FileConfigurationSource`  ->  `com.solid.test.unit.configuration.FileConfigurationSourceTest`

---

# Naming Conventions

- Each test method starts with a verb and describes the expected behavior.
- Use the style `shouldDoSomethingWhenCondition`.

Examples:
    - `shouldReturnPropertiesWhenFileIsLoaded`
    - `shouldThrowIOExceptionWhenFileNotFound`

---

# Best Practices

- One assert per test (Single Assertion Principle).
- No real database access in unit tests.
- Mock only what is necessary.
- Do not test libraries or frameworks, only the behavior of your own code.
- Tests should be fast and independent.
- Keep logic clean and isolated.

---

# Tools Used

- JUnit 5
- Mockito (for mocking)
- H2 or MySQL (integration tests only, depending on the context)
- Flyway (for applying migrations in integration tests only)

---

# Useful Commands

Run all tests:  
    `mvn test`

Run only unit tests:  
    `mvn -Dtest=**/unit/** test`

Run only integration tests:  
    `mvn -Dtest=**/integration/** test`

---

# What We Test

- The expected behavior of a class in isolation.
- Observable effects (return values, state changes, method calls).
- Critical conditional branches.

---

# What We Don’t Test

- Simple getters/setters.
- Generated or annotation-only code with no business logic.
- Third-party libraries (e.g., Hibernate, JPA, JDBC, etc.)
