package com.solid.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.solid.exceptions.JpaProviderException;
import jakarta.persistence.EntityManagerFactory;

public class HibernateProvider implements IJpaProvider {

    private static final Logger logger = LoggerFactory.getLogger(HibernateProvider.class);

    private IConfigurationSource configurationSource;
    private EntityScanner entityScanner;
    private SessionFactory sessionFactory;

    public HibernateProvider(IConfigurationSource configurationSource, EntityScanner entityScanner) {
        this.configurationSource = configurationSource;
        this.entityScanner = entityScanner;
        this.sessionFactory = this.buildSessionFactory();

    }

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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return sessionFactory;
    }

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