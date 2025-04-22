package com.yinnohs.security.jwt.auth.domain.entities;

import com.yinnohs.security.jwt.auth.domain.vos.Email;
import com.yinnohs.security.jwt.auth.domain.vos.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account account;
    private Email email;
    private Password password;
    private Role role;

    @BeforeEach
    void setUp() {
        email = new Email("test@example.com");
        password = new Password("ValidPass123!@#");
        role = new Role();
        role.setName("ROLE_USER");

        account = Account.builder()
                .id(1L)
                .email(email)
                .password(password)
                .refreshToken("refreshToken")
                .userId(1L)
                .role(role)
                .createdAt(LocalDateTime.now())
                .lastUpdate(LocalDateTime.now())
                .build();
    }

    @Test
    void shouldCreateAccountWithAllProperties() {
        // Then
        assertNotNull(account);
        assertEquals(1L, account.getId());
        assertEquals(email, account.getEmail());
        assertEquals(password, account.getPassword());
        assertEquals("refreshToken", account.getRefreshToken());
        assertEquals(1L, account.getUserId());
        assertEquals(role, account.getRole());
        assertNotNull(account.getCreatedAt());
        assertNotNull(account.getLastUpdate());
    }

    @Test
    void shouldUpdateRefreshToken() {
        // Given
        String newRefreshToken = "newRefreshToken";

        // When
        account.setRefreshToken(newRefreshToken);

        // Then
        assertEquals(newRefreshToken, account.getRefreshToken());
    }

    @Test
    void shouldUpdateLastUpdateTimestamp() {
        // Given
        LocalDateTime newTimestamp = LocalDateTime.now().plusDays(1);

        // When
        account.setLastUpdate(newTimestamp);

        // Then
        assertEquals(newTimestamp, account.getLastUpdate());
    }

    @Test
    void shouldHandleNullValues() {
        // Given
        Account nullAccount = new Account();

        // Then
        assertNull(nullAccount.getId());
        assertNull(nullAccount.getEmail());
        assertNull(nullAccount.getPassword());
        assertNull(nullAccount.getRefreshToken());
        assertNull(nullAccount.getUserId());
        assertNull(nullAccount.getRole());
        assertNull(nullAccount.getCreatedAt());
        assertNull(nullAccount.getLastUpdate());
    }

    @Test
    void shouldUpdateRole() {
        // Given
        Role newRole = new Role();
        newRole.setName("ROLE_ADMIN");

        // When
        account.setRole(newRole);

        // Then
        assertEquals(newRole, account.getRole());
    }
} 