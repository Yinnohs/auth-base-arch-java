package com.yinnohs.security.jwt.user.application.usecases;

import com.yinnohs.security.jwt.user.domain.ports.out.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExistByEmailTest {

    @Mock
    private UserService userService;

    private ExistByEmail existByEmail;

    @BeforeEach
    void setUp() {
        existByEmail = new ExistByEmail(userService);
    }

    @Test
    void execute_WhenEmailExists_ShouldReturnTrue() {
        // Given
        String existingEmail = "existing@example.com";
        when(userService.existByEmail(existingEmail)).thenReturn(true);

        // When
        boolean result = existByEmail.execute(existingEmail);

        // Then
        assertTrue(result);
    }

    @Test
    void execute_WhenEmailDoesNotExist_ShouldReturnFalse() {
        // Given
        String nonExistingEmail = "nonexisting@example.com";
        when(userService.existByEmail(nonExistingEmail)).thenReturn(false);

        // When
        boolean result = existByEmail.execute(nonExistingEmail);

        // Then
        assertFalse(result);
    }

    @Test
    void execute_WhenEmailIsNull_ShouldReturnFalse() {
        // Given
        when(userService.existByEmail(null)).thenReturn(false);

        // When
        boolean result = existByEmail.execute(null);

        // Then
        assertFalse(result);
    }
} 