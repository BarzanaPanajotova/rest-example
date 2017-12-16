package com.bdpanajoto.ws.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdpanajoto.ws.rest.domain.Group;
import com.bdpanajoto.ws.rest.repository.GroupRepository;
import com.bdpanajoto.ws.rest.resource.GroupResource;
import com.bdpanajoto.ws.rest.resource.GroupResourceAssembler;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(Group.class)
@RequestMapping(value = "/groups", produces = "application/json")
public class GroupController extends AbstractController<Group, GroupResource> {
	@Autowired
	public GroupController(GroupRepository repository, GroupResourceAssembler assembler) {
		super.repository = repository;
		super.assembler = assembler;
	}
}
