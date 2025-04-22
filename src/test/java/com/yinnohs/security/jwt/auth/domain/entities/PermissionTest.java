package com.yinnohs.security.jwt.auth.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PermissionTest {

    private Permission permission;

    @BeforeEach
    void setUp() {
        permission = new Permission();
        permission.setId(1L);
        permission.setName("READ_USER");
    }

    @Test
    void shouldCreatePermissionWithAllProperties() {
        // Then
        assertNotNull(permission);
        assertEquals(1L, permission.getId());
        assertEquals("READ_USER", permission.getName());
    }

    @Test
    void shouldUpdatePermissionName() {
        // Given
        String newName = "WRITE_USER";

        // When
        permission.setName(newName);

        // Then
        assertEquals(newName, permission.getName());
    }

    @Test
    void shouldHandleNullValues() {
        // Given
        Permission nullPermission = new Permission();

        // Then
        assertNull(nullPermission.getId());
        assertNull(nullPermission.getName());
    }

    @Test
    void shouldUpdateId() {
        // Given
        Long newId = 2L;

        // When
        permission.setId(newId);

        // Then
        assertEquals(newId, permission.getId());
    }
} 