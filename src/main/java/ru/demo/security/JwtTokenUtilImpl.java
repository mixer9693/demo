package ru.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.demo.entity.User;

import java.util.Date;

@Component
public class JwtTokenUtilImpl implements JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Integer tokenLifetimeSec;

    @Override
    public String generateToken(User user) {
        Date now = new Date();
        Date exp = new Date(System.currentTimeMillis() + (1000L * tokenLifetimeSec));

        return Jwts.builder()
                .claim("name", user.getName())
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    @Override
    public boolean validate(String token) {
        try {
            return extractClaims(token).getExpiration().after(new Date());
        } catch (RuntimeException e) {
            return false;
        }
    }

    @Override
    public String getUsername(String token) {
        return extractClaims(token).get("name", String.class);
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

}


