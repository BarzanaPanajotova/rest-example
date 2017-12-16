package com.bdpanajoto.ws.rest.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bdpanajoto.ws.rest.domain.Group;

@Repository
public class GroupRepository extends InMemoryRepository<Group> {
	@Autowired
	private UserRepository users;

	protected void updateIfExists(Group original, Group updated) {
		if (!users.elements.stream().anyMatch(user -> updated.getUser().getUsername().equals(user.getUsername()))) {
			throw new IllegalArgumentException("User does not exist!");
		}
		if (!elements.isEmpty() && contains(elements, updated.getName(), updated.getUser().getUsername())) {
			throw new IllegalArgumentException("Can not add the same group twice to the same user!");
		}
		original.setName(updated.getName());
		original.setUser(updated.getUser());
	}

	@Override
	public Group create(Group element) {
		if (element.getUser() != null && !users.elements.stream()
				.anyMatch(user -> element.getUser().getUsername().equals(user.getUsername()))) {
			throw new IllegalArgumentException("User does not exist!");
		}
		if (!elements.isEmpty() && contains(elements, element.getName(), element.getUser().getUsername())) {
			throw new IllegalArgumentException("Can not add the same group twice to the same user!");
		}
		return super.create(element);
	}

	private boolean contains(List<Group> elements, String groupName, String userName) {
		return elements.stream()
				.anyMatch(group -> groupName.equals(group.getName()) && userName.equals(group.getUser().getUsername()));
	}
}