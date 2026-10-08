package br.com.unitalent.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SimpleRateLimitFilter implements org.springframework.cloud.gateway.filter.GlobalFilter, Ordered {
    private final ConcurrentHashMap<String, Window> windows = new ConcurrentHashMap<>();
    private static final int MAX_REQUESTS = 60;
    private static final long WINDOW_MS = 60_000;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String key = exchange.getRequest().getRemoteAddress() == null
            ? "unknown"
            : exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();

        Window w = windows.computeIfAbsent(key, k -> new Window());
        long now = System.currentTimeMillis();
        synchronized (w) {
            if (now - w.startedAt > WINDOW_MS) {
                w.startedAt = now;
                w.count.set(0);
            }
            if (w.count.incrementAndGet() > MAX_REQUESTS) {
                exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.TOO_MANY_REQUESTS);
                return exchange.getResponse().setComplete();
            }
        }
        return chain.filter(exchange);
    }

    @Override public int getOrder() { return -100; }

    static class Window {
        long startedAt = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger();
    }
}
