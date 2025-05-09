package com.solid.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.solid.exceptions.JpaProviderException;
import jakarta.persistence.EntityManagerFactory;

/**
 * Provides JPA integration using Hibernate as the underlying implementation.
 * It builds and manages a Hibernate SessionFactory using configuration
 * properties
 * and entity scanning.
 */
public class HibernateProvider implements IJpaProvider {

    private static final Logger logger = LoggerFactory.getLogger(HibernateProvider.class);

    private IConfigurationSource configurationSource;
    private EntityScanner entityScanner;
    private SessionFactory sessionFactory;

    /**
     * Constructs a HibernateProvider with the given configuration source and entity
     * scanner.
     *
     * @param configurationSource source of Hibernate configuration properties
     * @param entityScanner       scanner for discovering entity classes
     */
    public HibernateProvider(IConfigurationSource configurationSource, EntityScanner entityScanner) {
        this.configurationSource = configurationSource;
        this.entityScanner = entityScanner;
        this.sessionFactory = this.buildSessionFactory();
    }

    /**
     * Builds and configures the Hibernate SessionFactory.
     *
     * @return the configured SessionFactory
     * @throws RuntimeException if the SessionFactory creation fails
     */
    private SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        try {
            logger.info("Loading configuration properties.");
            configuration.addProperties(configurationSource.load());

            this.addAnnotatedEntities(configuration);

            return configuration.buildSessionFactory();

        } catch (Exception exception) {
            String errorMessage = "Error occurred while creating the SessionFactory";
            logger.error(errorMessage, exception);
            throw new RuntimeException(errorMessage, exception);
        }
    }

    /**
     * Adds all @Entity annotated classes discovered by the scanner
     * to the Hibernate configuration.
     *
     * @param configuration the Hibernate configuration instance
     */
    private void addAnnotatedEntities(Configuration configuration) {
        logger.info("Scanning for @Entity annotated classes.");

        entityScanner.scanForEntities().forEach(entityClass -> {
            try {
                configuration.addAnnotatedClass(entityClass);
                logger.info("Entity {} added to configuration.", entityClass.getName());
            } catch (Exception exception) {
                logger.error("Failed to add entity {} to configuration", entityClass.getName(), exception);
            }
        });
    }

    /**
     * Returns the underlying Hibernate SessionFactory.
     *
     * @return the SessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Returns the SessionFactory as a JPA EntityManagerFactory.
     *
     * @return the EntityManagerFactory
     */
    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return sessionFactory;
    }

    /**
     * Closes the SessionFactory if it is open.
     *
     * @throws JpaProviderException if closing fails
     */
    @Override
    public void close() throws JpaProviderException {
        if (sessionFactory != null && sessionFactory.isOpen()) {
            try {
                logger.info("Closing the SessionFactory...");
                sessionFactory.close();
                logger.info("SessionFactory closed successfully.");

            } catch (Exception exception) {
                String errorMessage = "Failed to close the SessionFactory";
                logger.error(errorMessage, exception);
                throw new JpaProviderException(errorMessage, exception);
            }
        }
    }
}
