package com.jpan.togglegateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ToggleGatewayApplication {

	@Value("${backend.api.url:http://host.docker.internal:8080/}")
	private String backendApiUrl;

	public static void main(String[] args) {
		SpringApplication.run(ToggleGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator featureToggleRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r
						.path("/features/toomany")
						.uri(backendApiUrl + "features/toomany")
				).build();
	}
}
