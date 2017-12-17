package com.bdpanajoto.ws.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import com.bdpanajoto.ws.rest.domain.Plot;
import com.bdpanajoto.ws.rest.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlotResource extends ResourceSupport {

	private final Long id;
	private final String name;
	private final String coordinates;
	private final String culture;
	private final User user;

	public PlotResource(Plot plot) {
		id = plot.getId();
		name = plot.getName();
		coordinates = plot.getCoordinates();
		culture = plot.getCulture();
		user = plot.getUser();
	}

	@JsonProperty("id")
	public Long getResourceId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public String getCulture() {
		return culture;
	}

	public User getUser() {
		return user;
	}

}