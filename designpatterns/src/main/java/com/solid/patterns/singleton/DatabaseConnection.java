package com.solid.patterns.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.solid.patterns.configuration.DatabaseConfig;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    private DatabaseConfig databaseConfig;
    private Connection connection;

    private DatabaseConnection() {

        this.databaseConfig = new DatabaseConfig();
        this.initConnection();
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    private void initConnection() {
        try {
            this.connection = DriverManager.getConnection(
                    this.databaseConfig.getDbUrl(),
                    this.databaseConfig.getUsername(),
                    this.databaseConfig.getPassword());

            System.out.println("Connexion réussie ! ");

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
