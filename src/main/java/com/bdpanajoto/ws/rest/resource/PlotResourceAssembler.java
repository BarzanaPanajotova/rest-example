package com.bdpanajoto.ws.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.bdpanajoto.ws.rest.domain.Plot;

@Component
public class PlotResourceAssembler extends ResourceAssembler<Plot, PlotResource> {

	@Autowired
	protected EntityLinks entityLinks;

	private static final String UPDATE_REL = "update";
	private static final String DELETE_REL = "delete";

	@Override
	public PlotResource toResource(Plot plot) {

		PlotResource resource = new PlotResource(plot);

		final Link selfLink = entityLinks.linkToSingleResource(plot);

		resource.add(selfLink.withSelfRel());
		resource.add(selfLink.withRel(UPDATE_REL));
		resource.add(selfLink.withRel(DELETE_REL));
		return resource;
	}
}