package ru.demo.service;

import ru.demo.dto.JwtTokenDto;
import ru.demo.dto.LoginDto;

public interface JwtAuthenticationService {
    JwtTokenDto createJwt(LoginDto loginDto);
}
