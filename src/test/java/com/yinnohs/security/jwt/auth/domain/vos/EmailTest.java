package com.yinnohs.security.jwt.auth.domain.vos;

import com.yinnohs.security.jwt.auth.domain.exceptions.InvalidEmailException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void shouldCreateEmailWithValidFormat() {
        // Given
        String validEmail = "test@example.com";

        // When
        Email email = new Email(validEmail);

        // Then
        assertNotNull(email);
        assertEquals(validEmail, email.getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "invalid-email",
            "invalid@",
            "@invalid.com",
            "invalid@.com",
            "invalid@com",
            "invalid@domain..com",
            "invalid@domain.com.",
            "invalid@domain.com.."
    })
    void shouldThrowExceptionForInvalidEmailFormats(String invalidEmail) {
        // When & Then
        assertThrows(InvalidEmailException.class, () -> new Email(invalidEmail));
    }

    @Test
    void shouldHandleEmailWithSpecialCharacters() {
        // Given
        String emailWithSpecialChars = "test.user+label@example.com";

        // When
        Email email = new Email(emailWithSpecialChars);

        // Then
        assertNotNull(email);
        assertEquals(emailWithSpecialChars, email.getValue());
    }

    @Test
    void shouldHandleEmailWithSubdomain() {
        // Given
        String emailWithSubdomain = "test@sub.domain.com";

        // When
        Email email = new Email(emailWithSubdomain);

        // Then
        assertNotNull(email);
        assertEquals(emailWithSubdomain, email.getValue());
    }
} 