package ru.demo.security;

import ru.demo.entity.User;

public interface JwtTokenUtil {
    boolean validate(String token);

    String getUsername(String token);

    String generateToken(User user);
}
