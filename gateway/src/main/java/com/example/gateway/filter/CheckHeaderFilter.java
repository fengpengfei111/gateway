package com.example.gateway.filter;

import com.example.gateway.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CheckHeaderFilter implements GlobalFilter {
    @Autowired
    RedisUtil redisUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest req = exchange.getRequest().mutate()
                .header("from", "gateway").build();
        // 上游给Token   // auth服务解析Token
        //restTe  Group
        //contains 1  2  3  4  5
        return chain.filter(exchange.mutate().request(req.mutate().build()).build());
    }
}
