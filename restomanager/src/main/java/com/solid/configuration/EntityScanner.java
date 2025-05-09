package com.solid.configuration;

import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Entity;

/**
 * Utility class responsible for scanning a given package to find all classes
 * annotated with @Entity.
 * This is typically used in JPA/Hibernate contexts to dynamically discover
 * entity classes.
 */
public class EntityScanner {
    private static final Logger logger = LoggerFactory.getLogger(EntityScanner.class);

    private String packageName;

    /**
     * Constructs an EntityScanner with the specified base package.
     * 
     * @param packageName the package to scan for entity classes
     */
    public EntityScanner(String packageName) {
        this.packageName = packageName;
    }

    /**
     * Scans the specified package for classes annotated with @Entity.
     * 
     * @return a set of classes annotated with @Entity
     */
    public Set<Class<?>> scanForEntities() {
        logger.info("Scanning for @Entity annotated classes in package : {}", packageName);

        Reflections reflections = new Reflections(this.packageName);
        Set<Class<?>> annotatededClasses = reflections.getTypesAnnotatedWith(Entity.class);

        logger.info("Found {} @Entity annotated classes.", annotatededClasses.size());
        return annotatededClasses;
    }
}
