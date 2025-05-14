# Resto Manager - Restaurant Management System

A restaurant management application built with **Java 17**, **JavaFX 13**, **Hibernate 6**, **Lombok**, and **Reflections**. This application uses a modular and scalable architecture with an abstract interface `IJpaProvider`, allowing it to remain independent of Hibernate.

## Features
- **Restaurant Management**: Create, modify, and delete one or more restaurants.
- **Ticket Management**: Register customers with a numeric ticket upon arrival, allowing tracking of their position in the queue.
- **Order Management**: Take orders, track the order status in real time, and serve the dishes.
- **Resource Management**: Track the availability of tables and plates to ensure smooth service.
- **Bill Management**: Generate the bill and handle payment (by card, cash, etc.).

## Technologies
- Java 17
- JavaFX 13 (without FXML)
- Hibernate ORM 6
- MySQL
- PostgreSQL (optional, with specific configuration file)
- Maven
- Lombok
- Reflections (org.reflections:reflections)

## Maven Structure
restaurant-system/  
├── pom.xml  
├── README.md  
├── src/  
│   └── main/  
│       ├── java/  
│       │   └── com/solid/  
│       │       ├── App.java  
│       │       ├── entities/  
│       │       ├── dao/  
│       │       ├── services/  
│       │       ├── managers/  
│       │       ├── controllers/  
│       │       ├── models/  
│       │       ├── views/  
│       │       └── configuration/  
│       │           ├── IJpaProvider.java  
│       │           └── HibernateProvider.java  
│       └── resources/  
│           └── config/  
│               ├── hibernate.config.properties  
│               └── postgresql.config.properties (optional for PostgreSQL)  

## Installation
1. Clone the project  
   `git clone https://github.com/your-user/restaurant-system.git`  
   `cd restaurant-system`

2. Create the MySQL database (default)  
   `CREATE DATABASE restaurants_db;`  

   **For MySQL:**  
   If you are using MySQL, you can create the database with the provided SQL file.  
   The SQL file for MySQL is located in the `resources/sql/` directory under the name `restaurants_db.sql`. You can execute it directly in your MySQL database via MySQL Workbench, the command line, or any other database management tool.

   **For PostgreSQL:**  
   If you are using PostgreSQL, you can create the database with the provided SQL file.  
   The SQL file for PostgreSQL is located in the `resources/sql/` directory under the name `restaurants_db.pg.sql`. You can execute it directly in your PostgreSQL database via PGAdmin or the command line.

3. Configure Hibernate  
   The `src/main/resources/config/hibernate.config.properties` file contains the Hibernate configuration for MySQL by default.  

   ### To use PostgreSQL:  
   - Comment out the MySQL section in this file.  
   - Uncomment the PostgreSQL section.  
   - Adjust the connection details if needed (username, password, etc.).  
   - In the `pom.xml` file:  
     - Comment out the MySQL dependency.  
     - Uncomment the PostgreSQL dependency.
4. Run the project  
   `mvn clean install`  
   `mvn exec:java -Dexec.mainClass="com.solid.App"`

## Architecture
The application follows a modular architecture inspired by an extended MVC pattern:
- `entities/` – JPA Entities
- `dao/` – Data Access (Hibernate CRUD)
- `service/` – Business Logic
- `managers/` – Use case coordination
- `controllers/` – User interaction (JavaFX)
- `models/` – Display models for the UI
- `views/` – JavaFX UI written in pure Java
- `configuration/` – Abstract JPA provider (`IJpaProvider`) and Hibernate implementation (`HibernateProvider`)

The use of `Reflections` allows dynamic detection of certain classes, including entities.

## Libraries used
- JavaFX 13  
- Hibernate ORM 6  
- Lombok  
- Reflections (org.reflections:reflections)  
- MySQL JDBC Driver (version 8.3.0)  
- PostgreSQL JDBC Driver (version 42.7.5)

## Notes

- By default, the project uses MySQL. To use PostgreSQL, you need to modify the configuration in `hibernate.config.properties` and adjust the dependencies in the `pom.xml`.
- An SQL file for MySQL is provided in the `resources/sql/` directory under the name `restaurants_db.sql`. This file contains the database structure with the necessary tables for a MySQL database.
- An SQL file for PostgreSQL is also provided in the `resources/sql/` directory under the name `restaurants_db.pg.sql`. This file contains the database structure with the necessary tables for a PostgreSQL database.
- No heavy framework (Spring, etc.)
- Configuration and lifecycle managed manually
- `IJpaProvider` interface to decentralize JPA configuration
- 100% JavaFX UI without FXML, for complete control over the interface


## License
This project is licensed under the [MIT License](https://opensource.org/licenses/MIT). See the [LICENSE](./LICENSE) file for details.


## Author
Developed by SolidCodeApp  
A modular, maintainable, and clear-architecture Java educational project.
