package com.yinnohs.security.jwt.auth.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
    }

    @Test
    void shouldCreateRoleWithAllProperties() {
        // Then
        assertNotNull(role);
        assertEquals(1L, role.getId());
        assertEquals("ROLE_USER", role.getName());
    }

    @Test
    void shouldUpdateRoleName() {
        // Given
        String newName = "ROLE_ADMIN";

        // When
        role.setName(newName);

        // Then
        assertEquals(newName, role.getName());
    }

    @Test
    void shouldHandleNullValues() {
        // Given
        Role nullRole = new Role();

        // Then
        assertNull(nullRole.getId());
        assertNull(nullRole.getName());
    }

    @Test
    void shouldUpdateId() {
        // Given
        Long newId = 2L;

        // When
        role.setId(newId);

        // Then
        assertEquals(newId, role.getId());
    }
} 