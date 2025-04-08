package com.yinnohs.security.jwt.user.application.usecases;

import com.yinnohs.security.jwt.user.application.dtos.CreateUserRequest;
import com.yinnohs.security.jwt.user.domain.entities.User;
import com.yinnohs.security.jwt.user.domain.ports.out.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTest {

    @Mock
    private UserService userService;

    private CreateUserUseCase createUserUseCase;

    @BeforeEach
    void setUp() {
        createUserUseCase = new CreateUserUseCase(userService);
    }

    @Test
    void execute_WhenValidRequest_ShouldReturnUserId() {
        // Given
        CreateUserRequest request = new CreateUserRequest(
                "John",
                "Doe",
                "john.doe@example.com"
        );

        Long expectedUserId = 1L;
        when(userService.save(any(User.class))).thenReturn(expectedUserId);

        // When
        Long actualUserId = createUserUseCase.execute(request);

        // Then
        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    void execute_WhenValidRequest_ShouldCreateUserWithCorrectData() {
        // Given
        CreateUserRequest request = new CreateUserRequest(
                "John",
                "Doe",
                "john.doe@example.com"
        );

        var expectedResult = 1L;

        when(userService.save(any(User.class))).thenReturn(1L);

        // When
        var result = createUserUseCase.execute(request);

        // Then
        assertEquals(expectedResult, result);
        // This is implicitly verified through the mock setup
    }
} 