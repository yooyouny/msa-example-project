package com.sparta.msa_exam.gateway.component;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

import java.net.URI;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AddSeverPortInResponseHeaderFilter implements GlobalFilter, Ordered {
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    return chain
        .filter(exchange)
        .then(
            Mono.fromRunnable(
                () -> {
                  URI uri = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
                  if (uri != null) {
                    String port = String.valueOf(uri.getPort());
                    exchange.getResponse().getHeaders().add("Server-Port", port);
                  }
                }));
  }

  @Override
  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }
}
