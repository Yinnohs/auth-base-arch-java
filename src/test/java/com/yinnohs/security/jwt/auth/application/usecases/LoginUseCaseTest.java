package com.yinnohs.security.jwt.auth.application.usecases;

import com.yinnohs.security.jwt.auth.application.dtos.LoginRequest;
import com.yinnohs.security.jwt.auth.application.dtos.LoginResponse;
import com.yinnohs.security.jwt.auth.domain.entities.Account;
import com.yinnohs.security.jwt.auth.domain.ports.out.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private LoginUseCase loginUseCase;

    private LoginRequest validRequest;
    private Account mockAccount;

    @BeforeEach
    void setUp() {
        validRequest = new LoginRequest(
                "john.doe@example.com",
                "ValidPass123!@#"
        );

        mockAccount = new Account();
        mockAccount.setEmail(new com.yinnohs.security.jwt.auth.domain.vos.Email(validRequest.email()));
    }

    @Test
    void shouldSuccessfullyLoginUser() {
        // Given
        String authToken = "authToken";
        String refreshToken = "refreshToken";
        when(accountService.getTokens(validRequest.email(), validRequest.password()))
                .thenReturn(Arrays.asList(authToken, refreshToken));
        when(accountService.findByEmail(validRequest.email())).thenReturn(mockAccount);
        when(accountService.save(any(Account.class))).thenReturn(1L);

        // When
        LoginResponse response = loginUseCase.execute(validRequest);

        // Then
        assertNotNull(response);
        assertEquals(authToken, response.authToken());
        assertEquals(refreshToken, response.refreshToken());
        verify(accountService).getTokens(validRequest.email(), validRequest.password());
        verify(accountService).findByEmail(validRequest.email());
        verify(accountService).save(any(Account.class));
    }

    @Test
    void shouldHandleInvalidCredentials() {
        // Given
        when(accountService.getTokens(validRequest.email(), validRequest.password()))
                .thenThrow(new RuntimeException("Invalid credentials"));

        // When & Then
        assertThrows(RuntimeException.class, () -> loginUseCase.execute(validRequest));
        verify(accountService, never()).findByEmail(any());
        verify(accountService, never()).save(any());
    }

    @Test
    void shouldHandleNullInputs() {
        // Given
        LoginRequest nullRequest = new LoginRequest(null, null);

        // When & Then
        assertThrows(NullPointerException.class, () -> loginUseCase.execute(nullRequest));
        verify(accountService, never()).getTokens(any(), any());
        verify(accountService, never()).findByEmail(any());
        verify(accountService, never()).save(any());
    }

    @Test
    void shouldHandleEmptyInputs() {
        // Given
        LoginRequest emptyRequest = new LoginRequest("", "");

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> loginUseCase.execute(emptyRequest));
        verify(accountService, never()).getTokens(any(), any());
        verify(accountService, never()).findByEmail(any());
        verify(accountService, never()).save(any());
    }
} 