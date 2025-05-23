package com.solid.configuration;

import org.reflections.Reflections;
import jakarta.persistence.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Utility class that scans for classes annotated with {@code @Entity}
 * using the Reflections library.
 */
public class EntityScanner {

    private static final Logger logger = LoggerFactory.getLogger(EntityScanner.class);

    private final Reflections reflections;

    /**
     * Constructs an EntityScanner using the provided Reflections instance.
     * This allows scanning to be decoupled from fixed package names,
     * making the class easier to test and reuse.
     *
     * @param reflections The Reflections instance used to find entity classes.
     */
    public EntityScanner(Reflections reflections) {
        this.reflections = reflections;
    }

    /**
     * Returns the set of classes annotated with {@code @Entity}
     * discovered using the configured Reflections context.
     *
     * @return A set of classes annotated with {@code @Entity}.
     */
    public Set<Class<?>> scanForEntities() {
        logger.info("Scanning for @Entity annotated classes...");

        Set<Class<?>> annotatedEntities = reflections.getTypesAnnotatedWith(Entity.class);

        logger.info("Found {} @Entity annotated classes.", annotatedEntities.size());

        return annotatedEntities;
    }
}
