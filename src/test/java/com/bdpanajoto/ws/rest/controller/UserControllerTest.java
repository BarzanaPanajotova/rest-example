package com.bdpanajoto.ws.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.mockito.Mockito;

import com.bdpanajoto.ws.rest.domain.User;
import com.bdpanajoto.ws.rest.repository.UserRepository;
import com.bdpanajoto.ws.rest.resource.UserResource;
import com.bdpanajoto.ws.rest.resource.UserResourceAssembler;

public class UserControllerTest extends ControllerTest<User, UserResource> {
	private UserRepository repository;
	private UserResourceAssembler assembler;
	private static User USER = new User();

	@Before
	public void setUp() {
		repository = Mockito.mock(UserRepository.class);
		assembler = Mockito.mock(UserResourceAssembler.class);

		super.setController(new UserController(repository, assembler));
	}

	@Override
	protected void addMockedElementToRepo() {
		List<User> users = new ArrayList<>();
		users.add(USER);
		Mockito.when(repository.findAll()).thenReturn(users);
		Mockito.when(repository.create(USER)).thenReturn(USER);

		List<UserResource> resources = new ArrayList<>();
		UserResource resource = new UserResource(USER);
		resources.add(resource);
		Mockito.when(assembler.toResourceCollection(users)).thenReturn(resources);
		Mockito.when(assembler.toResource(USER)).thenReturn(resource);
	}

	@Override
	protected User getMockedDomainType() {
		return USER;
	}

}
