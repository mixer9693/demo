package ru.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.demo.dto.JwtTokenDto;
import ru.demo.dto.LoginDto;
import ru.demo.service.JwtAuthenticationService;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final JwtAuthenticationService jwtAuthenticationService;

    @PostMapping("/auth/create-token")
    public JwtTokenDto createToken(@RequestBody LoginDto loginDto) {
        return jwtAuthenticationService.createJwt(loginDto);
    }
}
