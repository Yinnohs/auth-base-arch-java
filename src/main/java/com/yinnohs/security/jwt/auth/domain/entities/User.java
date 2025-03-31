package com.yinnohs.security.jwt.auth.domain.entities;

import com.yinnohs.security.jwt.user.domain.vo.Email;
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
}
