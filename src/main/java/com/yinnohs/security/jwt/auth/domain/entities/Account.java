package com.yinnohs.security.jwt.auth.domain.entities;

import com.yinnohs.security.jwt.auth.domain.vos.Email;
import com.yinnohs.security.jwt.auth.domain.vos.Password;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Account {
    private Long id;
    private Email email;
    private Password password;
    private String refreshToken;
    private Long userId;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;

}
