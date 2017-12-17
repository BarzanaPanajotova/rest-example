package com.bdpanajoto.ws.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import com.bdpanajoto.ws.rest.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResource extends ResourceSupport {

	private final Long id;
	private final String name;
	private final String email;
	private final int age;
	private final String username;
	private final String password;

	public UserResource(User user) {
		id = user.getId();
		name = user.getName();
		email = user.getEmail();
		age = user.getAge();
		username = user.getUsername();
		password = user.getPassword();
	}

	@JsonProperty("id")
	public Long getResourceId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getAge() {
		return age;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}