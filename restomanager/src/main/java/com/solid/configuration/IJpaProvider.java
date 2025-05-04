package com.solid.configuration;

import com.solid.exceptions.JpaProviderException;
import jakarta.persistence.EntityManagerFactory;

public interface IJpaProvider {

    public EntityManagerFactory getEntityManagerFactory();

    public void close() throws JpaProviderException;

}
