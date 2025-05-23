package com.solid.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reflections.Reflections;

import jakarta.persistence.Entity;

@ExtendWith(MockitoExtension.class)
public class EntityScannerTest {

    @Mock
    private Reflections reflections;

    @InjectMocks
    private EntityScanner entityScanner;

    public class FakeEntity {
    }

    @Test
    void shouldReturnSetOfEntities() {

        // Given
        Set<Class<?>> expectedEntities = Set.of(FakeEntity.class);

        when(reflections.getTypesAnnotatedWith(Entity.class)).thenReturn(expectedEntities);

        // When
        Set<Class<?>> foundEntities = entityScanner.scanForEntities();

        // Then
        assertEquals(expectedEntities, foundEntities,
                "The scanned entities do not match the expected result");
    }

    @Test
    void shouldReturnEmptySet_WhenNoEntityFound() {
        // Given
        when(reflections.getTypesAnnotatedWith(Entity.class)).thenReturn(Set.of());

        // When
        Set<Class<?>> foundEntities = entityScanner.scanForEntities();

        // Then
        assertTrue(foundEntities.isEmpty());
    }
}