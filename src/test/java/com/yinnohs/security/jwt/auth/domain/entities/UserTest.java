package com.yinnohs.security.jwt.auth.domain.entities;

import com.yinnohs.security.jwt.user.domain.vo.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private Email email;

    @BeforeEach
    void setUp() {
        email = new Email("john.doe@example.com");
        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail(email);
    }

    @Test
    void shouldCreateUserWithAllProperties() {
        // Then
        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals(email, user.getEmail());
    }

    @Test
    void shouldUpdateUserProperties() {
        // Given
        String newFirstName = "Jane";
        String newLastName = "Smith";
        Email newEmail = new Email("jane.smith@example.com");

        // When
        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        user.setEmail(newEmail);

        // Then
        assertEquals(newFirstName, user.getFirstName());
        assertEquals(newLastName, user.getLastName());
        assertEquals(newEmail, user.getEmail());
    }

    @Test
    void shouldHandleNullValues() {
        // Given
        User nullUser = new User();

        // Then
        assertNull(nullUser.getId());
        assertNull(nullUser.getFirstName());
        assertNull(nullUser.getLastName());
        assertNull(nullUser.getEmail());
    }

    @Test
    void shouldUpdateId() {
        // Given
        Long newId = 2L;

        // When
        user.setId(newId);

        // Then
        assertEquals(newId, user.getId());
    }
} 