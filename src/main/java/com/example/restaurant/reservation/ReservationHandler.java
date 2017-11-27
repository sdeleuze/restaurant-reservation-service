package com.example.restaurant.reservation;

import java.net.URI;
import java.time.Duration;
import java.util.Random;

import reactor.core.publisher.Mono;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class ReservationHandler {

	private static final Random random = new Random();

	public Mono<ServerResponse> checkAvailability(ServerRequest request) {
		return ServerResponse.ok()
				.body(getAvailability(request), RestaurantAvailability.class);
	}

	private Mono<RestaurantAvailability> getAvailability(ServerRequest request) {
		String name = request.pathVariable("name");
		return Mono.delay(randomThinkTime()).then(randomAvailability())
				.map(availability -> new RestaurantAvailability(name, availability,
						availability ? confirmationUrl(name) : null));
	}

	/**
	 * Simulate restaurant figuring out if they have a table left.
	 */
	private static Duration randomThinkTime() {
		return Duration.ofMillis(1000 + random.nextInt(7000));
	}

	private static Mono<Boolean> randomAvailability() {
		return Mono.defer(() -> Mono.just(random.nextBoolean()));
	}

	private static URI confirmationUrl(String name) {
		return UriComponentsBuilder.fromUriString("/restaurants/{name}/book")
				.buildAndExpand(name).toUri();
	}

}
