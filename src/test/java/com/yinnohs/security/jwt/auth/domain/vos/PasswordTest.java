package com.yinnohs.security.jwt.auth.domain.vos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    void shouldCreatePasswordObject() {
        // Given
        String password = "ValidPass123!@#";

        // When
        Password passwordObj = new Password(password);

        // Then
        assertNotNull(passwordObj);
        assertEquals(password, passwordObj.getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "ValidPass123!@#",
            "AnotherValid1@Pass",
            "ComplexP@ssw0rd123",
            "Test123!@#$%^&*()"
    })
    void shouldValidateStrongPasswords(String strongPassword) {
        // When & Then
        assertTrue(Password.isValidPassword(strongPassword));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "short1!",
            "nouppercase1!",
            "NOLOWERCASE1!",
            "NoNumbers!",
            "NoSpecialChars123",
            "TooShort1!",
            "1234567890",
            "abcdefghijkl",
            "ABCDEFGHIJKL",
            "!@#$%^&*()_+"
    })
    void shouldRejectWeakPasswords(String weakPassword) {
        // When & Then
        assertFalse(Password.isValidPassword(weakPassword));
    }

    @Test
    void shouldHandleMinimumLengthRequirement() {
        // Given
        String minLengthPassword = "A1!bcdefghij"; // 12 characters

        // When & Then
        assertTrue(Password.isValidPassword(minLengthPassword));
    }

    @Test
    void shouldHandleMaximumLength() {
        // Given
        String longPassword = "A1!bcdefghijklmnopqrstuvwxyz1234567890"; // 36 characters

        // When & Then
        assertTrue(Password.isValidPassword(longPassword));
    }
} 