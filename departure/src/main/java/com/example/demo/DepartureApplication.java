package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class DepartureApplication {
	public static void main(String[] args) {

		SpringApplication.run(DepartureApplication.class, args);
	}

	@Bean
	public RouterFunction<ServerResponse> departureRoutes(DepartureHandler handler) {
		return route(GET("/departures"), handler::getAllDepartures)
				.andRoute(GET("/departures/{id}"), handler::getDeparture)
				.andRoute(POST("/departures").and(accept(MediaType.APPLICATION_JSON)), handler::createDeparture)
				.andRoute(PUT("/departures/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::updateDeparture)
				.andRoute(DELETE("/departures/{id}"),
						handler::deleteDeparture);
	}
}