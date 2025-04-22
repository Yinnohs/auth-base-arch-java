package com.yinnohs.security.jwt.auth.application.usecases;

import com.yinnohs.security.jwt.auth.application.dtos.SignUpRequest;
import com.yinnohs.security.jwt.auth.domain.entities.Account;
import com.yinnohs.security.jwt.auth.domain.entities.Role;
import com.yinnohs.security.jwt.auth.domain.entities.SecurityConstants;
import com.yinnohs.security.jwt.auth.domain.exceptions.InvalidEmailException;
import com.yinnohs.security.jwt.auth.domain.exceptions.InvalidPasswordException;
import com.yinnohs.security.jwt.auth.domain.ports.in.UserPort;
import com.yinnohs.security.jwt.auth.domain.ports.out.AccountService;
import com.yinnohs.security.jwt.auth.domain.ports.out.PasswordService;
import com.yinnohs.security.jwt.auth.domain.ports.out.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignUpUseCaseTest {

    @Mock
    private AccountService accountService;

    @Mock
    private UserPort userPort;

    @Mock
    private PasswordService passwordService;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private SignUpUseCase signUpUseCase;

    private SignUpRequest validRequest;
    private Role userRole;

    @BeforeEach
    void setUp() {
        validRequest = new SignUpRequest(
                "John",
                "Doe",
                "john.doe@example.com",
                "ValidPass123!@#"
        );
        userRole = new Role();
        userRole.setName(SecurityConstants.ROLE_USER);
    }

    @Test
    void shouldSuccessfullySignUpUser() {
        // Given
        when(userPort.existsByEmail(validRequest.email())).thenReturn(false);
        when(userPort.createUser(any(), any(), any())).thenReturn(1L);
        when(passwordService.hashPassword(any())).thenReturn("hashedPassword");
        when(roleService.finRoleByName(SecurityConstants.ROLE_USER)).thenReturn(userRole);
        when(accountService.save(any())).thenReturn(1L);

        // When
        Long accountId = signUpUseCase.execute(validRequest);

        // Then
        assertNotNull(accountId);
        assertEquals(1L, accountId);
        verify(userPort).createUser(validRequest.firstName(), validRequest.lastName(), validRequest.email());
        verify(passwordService).hashPassword(validRequest.password());
        verify(roleService).finRoleByName(SecurityConstants.ROLE_USER);
        verify(accountService).save(any(Account.class));
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        // Given
        when(userPort.existsByEmail(validRequest.email())).thenReturn(true);

        // When & Then
        assertThrows(InvalidEmailException.class, () -> signUpUseCase.execute(validRequest));
        verify(userPort, never()).createUser(any(), any(), any());
        verify(accountService, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsInvalid() {
        // Given
        SignUpRequest invalidPasswordRequest = new SignUpRequest(
                "John",
                "Doe",
                "john.doe@example.com",
                "weak"
        );

        // When & Then
        assertThrows(InvalidPasswordException.class, () -> signUpUseCase.execute(invalidPasswordRequest));
        verify(userPort, never()).createUser(any(), any(), any());
        verify(accountService, never()).save(any());
    }

    @Test
    void shouldHandleNullInputs() {
        // Given
        SignUpRequest nullRequest = new SignUpRequest(null, null, null, null);

        // When & Then
        assertThrows(InvalidPasswordException.class, () -> signUpUseCase.execute(nullRequest));
        verify(userPort, never()).createUser(any(), any(), any());
        verify(accountService, never()).save(any());
    }
} 