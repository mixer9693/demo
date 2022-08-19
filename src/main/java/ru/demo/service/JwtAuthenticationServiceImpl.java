package ru.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.demo.dto.JwtTokenDto;
import ru.demo.dto.LoginDto;
import ru.demo.entity.User;
import ru.demo.repository.UserRepository;
import ru.demo.security.JwtTokenUtil;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationServiceImpl implements JwtAuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public JwtTokenDto createJwt(LoginDto loginDto){
        User user = userRepository.findByName(loginDto.getUsername())
                .orElseThrow();

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){
            throw new BadCredentialsException(loginDto.getUsername());
        }

        String token = jwtTokenUtil.generateToken(user);

        return new JwtTokenDto(token);
    }
}
