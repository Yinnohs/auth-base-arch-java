package com.yinnohs.security.jwt.user.domain.ports.out;

import com.yinnohs.security.jwt.user.domain.entities.User;

public interface UserService {
     /*List<User> findAll();
     Optional<User> findById(Long userId);
     Optional<User> findByEmail(String email);*/
     Boolean existByEmail(String email);
     Long save(User user);
}
