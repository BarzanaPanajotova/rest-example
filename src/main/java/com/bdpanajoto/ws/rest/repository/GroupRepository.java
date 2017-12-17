package com.bdpanajoto.ws.rest.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bdpanajoto.ws.rest.domain.Group;
import com.bdpanajoto.ws.rest.domain.User;

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
		updated.setUser(
				users.elements.stream().filter(user -> updated.getUser().getUsername().equals(user.getUsername()))
						.collect(Collectors.toList()).get(0));
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
		element.setUser(
				users.elements.stream().filter(user -> element.getUser().getUsername().equals(user.getUsername()))
						.collect(Collectors.toList()).get(0));
		return super.create(element);
	}

	private boolean contains(List<Group> elements, String groupName, String userName) {
		return elements.stream()
				.anyMatch(group -> groupName.equals(group.getName()) && userName.equals(group.getUser().getUsername()));
	}

	public List<Group> getGroupsByUserId(Long id) {
		return elements.stream().filter(group -> id.equals(group.getId())).collect(Collectors.toList());
	}

	public List<User> getUsersByGroupName(String groupName) {
		List<Long> userIds = elements.stream().filter(group -> groupName.equals(group.getName()))
				.map(x -> x.getUser().getId()).collect(Collectors.toList());

		return users.elements.stream().filter(user -> userIds.contains(user.getId())).collect(Collectors.toList());
	}
}