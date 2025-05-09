package com.solid.configuration;

import com.solid.exceptions.JpaProviderException;
import jakarta.persistence.EntityManagerFactory;

/**
 * Defines a contract for providing and managing a JPA EntityManagerFactory.
 * Implementations are responsible for supplying the EntityManagerFactory and
 * handling its lifecycle.
 */
public interface IJpaProvider {

    /**
     * Returns the EntityManagerFactory instance used for creating EntityManagers.
     *
     * @return the EntityManagerFactory
     */
    EntityManagerFactory getEntityManagerFactory();

    /**
     * Closes the underlying EntityManagerFactory and releases any allocated
     * resources.
     *
     * @throws JpaProviderException if an error occurs during the closing process
     */
    void close() throws JpaProviderException;

}
