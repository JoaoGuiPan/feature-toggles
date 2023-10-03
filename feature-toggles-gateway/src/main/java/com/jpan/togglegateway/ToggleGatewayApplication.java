package com.jpan.togglegateway;

import com.jpan.togglegateway.bene.cache.BeNeRetryAfterCache;
import com.jpan.togglegateway.exception.BeNeTooManyRequestsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@EnableCaching
@SpringBootApplication
public class ToggleGatewayApplication {

	private final String backendApiUrl;

	private final BeNeRetryAfterCache cache;

	public static void main(String[] args) {
		SpringApplication.run(ToggleGatewayApplication.class, args);
	}

	public ToggleGatewayApplication(
			@Value("${backend.api.url:http://host.docker.internal:8080/}") final String backendApiUrl,
			final BeNeRetryAfterCache cache
	) {
		this.backendApiUrl = backendApiUrl;
		this.cache = cache;
	}

	@Bean
	public RouteLocator featureToggleRoutes(final RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r
						.path("/features/toomany")
						.filters(filters -> filters.filter(
								setRetryAfterHeader()
						))
						.uri(backendApiUrl + "features/toomany")
				).build();
	}

	private GatewayFilter setRetryAfterHeader() {
		return (exchange, chain) -> {

			if (cache.isPresentRetryAfterCache()) {
				final Integer retryAfterCache = cache.getRetryAfterCache();
				throw new BeNeTooManyRequestsException(retryAfterCache);
			}

			final Mono<Void> setRetryAfter = Mono.fromRunnable(() -> {
				final ServerHttpResponse response = exchange.getResponse();

				final int retryAfterHeader = getRetryAfterHeader(response);

				if (isTooManyRequests(response) && retryAfterHeader > 0) {
					cache.setRetryAfterCache(retryAfterHeader);
					response.getHeaders().put(
							HttpHeaders.RETRY_AFTER,
							List.of(
									String.valueOf(retryAfterHeader)
							)
					);
				}
			});

			return chain.filter(exchange).then(setRetryAfter);
		};
	}

	private int getRetryAfterHeader(final ServerHttpResponse response) {
		final List<String> retryAfter = response.getHeaders().get(HttpHeaders.RETRY_AFTER);
		if (retryAfter != null && !retryAfter.isEmpty()) {
			return Integer.parseInt(retryAfter.get(0));
		}
		return 0;
	}

	private boolean isTooManyRequests(final ServerHttpResponse response) {
		return response.getStatusCode() != null && HttpStatus.TOO_MANY_REQUESTS.compareTo(response.getStatusCode()) == 0;
	}
}
