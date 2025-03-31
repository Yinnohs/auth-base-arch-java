package com.yinnohs.security.jwt.user.domain.entities;

import com.yinnohs.security.jwt.user.domain.vo.Email;
import com.yinnohs.security.jwt.user.domain.vo.Password;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Email email;
    private Password password;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;
}
