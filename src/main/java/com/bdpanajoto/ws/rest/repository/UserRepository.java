package com.bdpanajoto.ws.rest.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.bdpanajoto.ws.rest.domain.User;

@Repository
public class UserRepository extends InMemoryRepository<User> {

	protected void updateIfExists(User original, User updated) {
		original.setName(updated.getName());
		original.setEmail(updated.getEmail());
		original.setAge(updated.getAge());
		original.setUsername(updated.getUsername());
		original.setPassword(updated.getPassword());
	}

	@Override
	public User create(User element) {

		if (!elements.isEmpty()
				&& elements.stream().anyMatch(user -> user.getUsername().equals(element.getUsername()))) {
			throw new IllegalArgumentException("A user with this username already exists!");
		}
		return super.create(element);
	}

	public Map<Integer, Integer> generateReport() {

		return elements.stream().collect(Collectors.groupingBy(User::getAge,
				Collectors.collectingAndThen(Collectors.mapping(User::getAge, Collectors.toList()), List::size)));
	}

}