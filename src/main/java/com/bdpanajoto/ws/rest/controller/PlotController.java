package com.bdpanajoto.ws.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdpanajoto.ws.rest.domain.Plot;
import com.bdpanajoto.ws.rest.repository.PlotRepository;
import com.bdpanajoto.ws.rest.resource.PlotResource;
import com.bdpanajoto.ws.rest.resource.PlotResourceAssembler;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(Plot.class)
@RequestMapping(value = "/plots", produces = "application/json")
public class PlotController extends AbstractController<Plot, PlotResource> {

	@Autowired
	public PlotController(PlotRepository repository, PlotResourceAssembler assembler) {
		super.repository = repository;
		super.assembler = assembler;
	}

}
