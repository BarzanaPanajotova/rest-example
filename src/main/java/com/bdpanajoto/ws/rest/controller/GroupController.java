package com.bdpanajoto.ws.rest.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
import com.bdpanajoto.ws.rest.domain.User;
import com.bdpanajoto.ws.rest.repository.GroupRepository;
import com.bdpanajoto.ws.rest.resource.GroupResource;
import com.bdpanajoto.ws.rest.resource.GroupResourceAssembler;
import com.bdpanajoto.ws.rest.resource.UserResource;
import com.bdpanajoto.ws.rest.resource.UserResourceAssembler;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(Group.class)
@RequestMapping(value = "/groups", produces = "application/json")
public class GroupController extends AbstractController<Group, GroupResource> {

	@Autowired
	private UserResourceAssembler userAssembler;

	@Autowired
	public GroupController(GroupRepository repository, GroupResourceAssembler assembler) {
		super.repository = repository;
		super.assembler = assembler;
	}

	@RequestMapping(value = "/{id}/users", method = RequestMethod.GET)
	public ResponseEntity<Collection<UserResource>> findGroupsUsers(@PathVariable Long id) {
		Optional<Group> group = repository.findById(id);

		if (!group.isPresent()) {
			List<User> element = ((GroupRepository) repository).getUsersByGroupName(group.get().getName());
			return new ResponseEntity<>(userAssembler.toResourceCollection(element), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
