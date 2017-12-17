package com.bdpanajoto.ws.rest.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bdpanajoto.ws.rest.domain.Group;
import com.bdpanajoto.ws.rest.domain.Plot;
import com.bdpanajoto.ws.rest.domain.User;
import com.bdpanajoto.ws.rest.repository.GroupRepository;
import com.bdpanajoto.ws.rest.repository.PlotRepository;
import com.bdpanajoto.ws.rest.repository.UserRepository;
import com.bdpanajoto.ws.rest.resource.GroupResource;
import com.bdpanajoto.ws.rest.resource.GroupResourceAssembler;
import com.bdpanajoto.ws.rest.resource.PlotResource;
import com.bdpanajoto.ws.rest.resource.PlotResourceAssembler;
import com.bdpanajoto.ws.rest.resource.UserResource;
import com.bdpanajoto.ws.rest.resource.UserResourceAssembler;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(User.class)
@RequestMapping(value = "/users", produces = "application/json")
public class UserController extends AbstractController<User, UserResource> {
	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private GroupResourceAssembler groupAssembler;
	@Autowired
	private PlotRepository plotRepository;
	@Autowired
	private PlotResourceAssembler plotAssembler;

	@Autowired
	public UserController(UserRepository repository, UserResourceAssembler assembler) {
		super.repository = repository;
		super.assembler = assembler;
	}

	@RequestMapping(value = "/{id}/groups", method = RequestMethod.GET)
	public ResponseEntity<Collection<GroupResource>> findUserGroups(@PathVariable Long id) {
		List<Group> element = groupRepository.getGroupsByUserId(id);

		if (!element.isEmpty()) {
			return new ResponseEntity<>(groupAssembler.toResourceCollection(element), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}/plots", method = RequestMethod.GET)
	public ResponseEntity<Collection<PlotResource>> findUserPlots(@PathVariable Long id) {
		List<Plot> element = plotRepository.getPlotsByUserId(id);

		if (!element.isEmpty()) {
			return new ResponseEntity<>(plotAssembler.toResourceCollection(element), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
