package com.bdpanajoto.ws.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdpanajoto.ws.rest.domain.User;
import com.bdpanajoto.ws.rest.repository.UserRepository;
import com.bdpanajoto.ws.rest.resource.UserResource;
import com.bdpanajoto.ws.rest.resource.UserResourceAssembler;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(User.class)
@RequestMapping(value = "/users", produces = "application/json")
public class UserController extends AbstractController<User, UserResource> {
	@Autowired
	public UserController(UserRepository repository, UserResourceAssembler assembler) {
		super.repository = repository;
		super.assembler = assembler;
	}
}
