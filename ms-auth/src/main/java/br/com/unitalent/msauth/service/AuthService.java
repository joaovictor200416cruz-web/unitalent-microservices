package br.com.unitalent.msauth.service;

import br.com.unitalent.msauth.dto.*;
import br.com.unitalent.msauth.model.Usuario;
import br.com.unitalent.msauth.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UsuarioRepository repository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UsuarioRepository repository, JwtService jwtService) {
        this.repository = repository; this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest req) {
        if (repository.existsByUsername(req.username())) {
            throw new IllegalArgumentException("Usuário já cadastrado");
        }
        String role = req.role() == null || req.role().isBlank() ? "USER" : req.role().toUpperCase();
        Usuario user = repository.save(new Usuario(req.username(), encoder.encode(req.password()), role));
        return new AuthResponse(jwtService.generate(user.getUsername(), user.getRole()), user.getUsername(), user.getRole());
    }

    public AuthResponse login(LoginRequest req) {
        Usuario user = repository.findByUsername(req.username())
            .orElseThrow(() -> new IllegalArgumentException("Credenciais inválidas"));
        if (!encoder.matches(req.password(), user.getPassword())) {
            throw new IllegalArgumentException("Credenciais inválidas");
        }
        return new AuthResponse(jwtService.generate(user.getUsername(), user.getRole()), user.getUsername(), user.getRole());
    }
}
