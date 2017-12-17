package com.bdpanajoto.ws.rest.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bdpanajoto.ws.rest.domain.Plot;

@Repository
public class PlotRepository extends InMemoryRepository<Plot> {
	@Autowired
	private UserRepository users;

	@Override
	protected void updateIfExists(Plot original, Plot desired) {
		if (desired.getUser() != null && !users.elements.stream()
				.anyMatch(user -> desired.getUser().getUsername().equals(user.getUsername()))) {
			throw new IllegalArgumentException("User does not exist!");
		}
		if (!elements.isEmpty() && contains(elements, desired.getName(), desired.getUser().getUsername())) {
			throw new IllegalArgumentException("Can not add the same plot twice to the same user!");
		}
		original.setName(desired.getName());
		original.setCoordinates(desired.getCoordinates());
		original.setCulture(desired.getCulture());
		original.setUser(desired.getUser());
	}

	@Override
	public Plot create(Plot element) {
		if (element.getUser() != null && !users.elements.stream()
				.anyMatch(user -> element.getUser().getUsername().equals(user.getUsername()))) {
			throw new IllegalArgumentException("User does not exist!");
		}
		if (!elements.isEmpty() && contains(elements, element.getName(), element.getUser().getUsername())) {
			throw new IllegalArgumentException("Can not add the same plot twice to the same user!");
		}
		return super.create(element);
	}

	private boolean contains(List<Plot> elements, String groupName, String userName) {
		return elements.stream()
				.anyMatch(group -> groupName.equals(group.getName()) && userName.equals(group.getUser().getUsername()));
	}
}
