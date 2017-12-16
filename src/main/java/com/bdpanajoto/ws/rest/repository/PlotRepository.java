package com.bdpanajoto.ws.rest.repository;

import org.springframework.stereotype.Repository;

import com.bdpanajoto.ws.rest.domain.Plot;

@Repository
public class PlotRepository extends InMemoryRepository<Plot> {

	@Override
	protected void updateIfExists(Plot original, Plot desired) {
		original.setName(desired.getName());
		original.setCoordinates(desired.getCoordinates());
		original.setCulture(desired.getCulture());
		original.setUser(desired.getUser());
	}

}
