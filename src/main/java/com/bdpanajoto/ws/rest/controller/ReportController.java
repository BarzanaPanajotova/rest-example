package com.bdpanajoto.ws.rest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bdpanajoto.ws.rest.domain.User;
import com.bdpanajoto.ws.rest.repository.UserRepository;
import com.bdpanajoto.ws.rest.resource.ReportResource;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(User.class)
@RequestMapping(value = "/report", produces = "application/json")
public class ReportController {
	@Autowired
	protected UserRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<ReportResource> generateReport() {
		Map<Integer, Integer> report = repository.generateReport();

		return new ResponseEntity<>(new ReportResource(report), HttpStatus.OK);

	}
}
