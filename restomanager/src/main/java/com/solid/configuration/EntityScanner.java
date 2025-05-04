package com.solid.configuration;

import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Entity;

public class EntityScanner {
    private static final Logger logger = LoggerFactory.getLogger(EntityScanner.class);

    private String packageName;

    public EntityScanner(String packageName) {
        this.packageName = packageName;
    }

    public Set<Class<?>> scanForEntities() {
        logger.info("Scanning for @Entity annotated classes in package : {}", packageName);

        Reflections reflections = new Reflections(this.packageName);
        Set<Class<?>> annotatededClasses = reflections.getTypesAnnotatedWith(Entity.class);

        logger.info("Found {} @Entity annotated classes.", annotatededClasses.size());
        return annotatededClasses;

    }

}
