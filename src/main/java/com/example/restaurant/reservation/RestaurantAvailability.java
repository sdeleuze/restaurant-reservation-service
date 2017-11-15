package com.example.restaurant.reservation;

import java.net.URI;

public class RestaurantAvailability {

	private final String name;

	private final boolean available;

	private final URI confirmationUri;

	RestaurantAvailability(String name, boolean available, URI confirmationUri) {
		this.name = name;
		this.available = available;
		this.confirmationUri = confirmationUri;
	}

	public String getName() {
		return this.name;
	}

	public boolean isAvailable() {
		return this.available;
	}

	public URI getConfirmationUri() {
		return this.confirmationUri;
	}

}
