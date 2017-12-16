package com.bdpanajoto.ws.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import com.bdpanajoto.ws.rest.domain.Group;
import com.bdpanajoto.ws.rest.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupResource extends ResourceSupport {
	private final Long id;
	private final String name;
	private final User user;


	public GroupResource(Group group) {
		id = group.getId();
		name = group.getName();
		user = group.getUser();
	}

	@JsonProperty("id")
	public Long getResourceId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public User getUser() {
		return user;
	}

}
