package com.yinnohs.security.jwt.user.domain.ports.out;

import com.yinnohs.security.jwt.user.domain.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
     /*List<User> findAll();
     Optional<User> findById(Long userId);
     Optional<User> findByEmail(String email);*/
     Long save(User user);
}
