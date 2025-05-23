package com.solid.configuration;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.solid.entities.*;

/**
 * Utility class to manage a singleton Hibernate SessionFactory.
 * It reads configuration from a properties file and registers all annotated
 * entities.
 */
public class HibernateUtil {

    private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

    private static SessionFactory sessionFactory;

    // List of all annotated entity classes used in the application
    private static final List<Class<?>> annotatedEntities = List.of(
            RestaurantEntity.class,
            TableEntity.class,
            TicketEntity.class,
            OrderEntity.class,
            MenuEntity.class,
            MenuItemEntity.class,
            OrderDishEntity.class,
            PlateEntity.class,
            OrderTableEntity.class);

    /**
     * Lazily initializes and returns the singleton instance of SessionFactory.
     * Reads configuration from 'hibernate.config.properties' and registers all
     * entities.
     *
     * @return the Hibernate SessionFactory
     * @throws RuntimeException if configuration loading or factory creation fails
     */
    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            logger.info("Creating SessionFactory instance...");

            try (InputStream input = HibernateUtil.class.getClassLoader()
                    .getResourceAsStream("config/hibernate.config.properties")) {

                if (input == null) {
                    logger.error("hibernate.config.properties file not found in the classpath");
                    throw new RuntimeException("Missing Hibernate configuration file");
                }

                // Load properties from the file
                Properties properties = new Properties();
                properties.load(input);

                Configuration configuration = new Configuration();
                configuration.addProperties(properties);

                // Add all annotated entity classes to the configuration
                annotatedEntities.forEach(configuration::addAnnotatedClass);

                // Build the SessionFactory
                sessionFactory = configuration.buildSessionFactory();

                logger.info("SessionFactory was successfully created.");

            } catch (Exception exception) {
                logger.error("Error while initializing Hibernate: {}", exception.getMessage(), exception);
                throw new RuntimeException("Failed to create SessionFactory", exception);
            }
        }

        return sessionFactory;
    }
}
