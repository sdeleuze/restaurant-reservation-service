package com.example.restaurant.reservation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class ReservationRoutes {

	@Bean
	public RouterFunction<?> routes(ReservationHandler handler) {
		return RouterFunctions.route(GET("/restaurants/{name}/availability"),
				handler::checkAvailability);
	}

}
