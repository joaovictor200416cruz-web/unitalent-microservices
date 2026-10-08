package br.com.unitalent.msauth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    private final SecretKey key = Keys.hmacShaKeyFor(
        "unitalent-tcc-chave-secreta-2026-com-no-minimo-32-caracteres".getBytes(StandardCharsets.UTF_8));

    public String generate(String username, String role) {
        return Jwts.builder()
            .subject(username)
            .claim("role", role)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000))
            .signWith(key)
            .compact();
    }
}
