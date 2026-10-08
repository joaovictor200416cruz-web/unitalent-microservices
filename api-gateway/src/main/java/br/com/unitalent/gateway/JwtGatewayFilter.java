package br.com.unitalent.gateway;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class JwtGatewayFilter implements org.springframework.cloud.gateway.filter.GlobalFilter {
    private final SecretKey key = Keys.hmacShaKeyFor(
        "unitalent-tcc-chave-secreta-2026-com-no-minimo-32-caracteres".getBytes(StandardCharsets.UTF_8));

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        if (path.startsWith("/api/auth/") || path.equals("/actuator/health") || path.equals("/")) {
            return chain.filter(exchange);
        }

        String header = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return unauthorized(exchange, "Token Bearer obrigatório");
        }

        try {
            Claims claims = Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(header.substring(7)).getPayload();
            exchange.getAttributes().put("usuario", claims.getSubject());
            exchange.getAttributes().put("role", claims.get("role", String.class));
            return chain.filter(exchange);
        } catch (Exception e) {
            return unauthorized(exchange, "Token inválido ou expirado");
        }
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange, String message) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = exchange.getResponse().bufferFactory()
            .wrap(("{\"erro\":\"" + message + "\"}").getBytes(StandardCharsets.UTF_8));
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }
}
