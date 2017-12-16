package com.bdpanajoto.ws.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import com.bdpanajoto.ws.rest.domain.Plot;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlotResource extends ResourceSupport {

	private final Long id;
	private final String name;

	public PlotResource(Plot plot) {
		id = plot.getId();
		name = plot.getName();
	}

	@JsonProperty("id")
	public Long getResourceId() {
		return id;
	}

	public String getName() {
		return name;
	}

}