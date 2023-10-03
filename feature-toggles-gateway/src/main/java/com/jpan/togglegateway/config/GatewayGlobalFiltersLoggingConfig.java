package com.jpan.togglegateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayGlobalFiltersLoggingConfig {

    final Logger logger = LoggerFactory.getLogger(GatewayGlobalFiltersLoggingConfig.class);

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> chain.filter(exchange)
                .then(Mono.fromRunnable(() -> logger.info("Gateway Post Filter executed")));
    }
}
