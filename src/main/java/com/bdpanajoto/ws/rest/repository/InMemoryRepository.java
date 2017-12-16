package com.bdpanajoto.ws.rest.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;

import com.bdpanajoto.ws.rest.domain.Identifiable;

public abstract class InMemoryRepository<T extends Identifiable> implements Repository<T> {

	@Autowired
	private IdGenerator idGenerator;

	protected List<T> elements = Collections.synchronizedList(new ArrayList<>());

	@Override
	public T create(T element) {
		elements.add(element);
		element.setId(idGenerator.getNextId());
		return element;
	}

	@Override
	public boolean delete(Long id) {
		return elements.removeIf(element -> element.getId().equals(id));
	}

	@Override
	public List<T> findAll() {
		return elements;
	}

	@Override
	public Optional<T> findById(Long id) {
		return elements.stream().filter(e -> e.getId().equals(id)).findFirst();
	}

	@Override
	public boolean update(Long id, T updated) {

		if (updated == null) {
			return false;
		} else {
			Optional<T> element = findById(id);
			element.ifPresent(original -> updateIfExists(original, updated));
			return element.isPresent();
		}
	}

	/**
	 * 
	 * @param page,
	 *            page >= 1
	 * @param size,
	 *            size >= 1
	 * @return a list representing the wanted page. An empty list if the page
	 *         was not found.
	 */
	@Override
	public List<T> findPaginated(Integer page, Integer size) {
		PagedListHolder<T> pagedListHolder = new PagedListHolder<>(elements);

		pagedListHolder.setPageSize(size);
		if (page < 1 || page > pagedListHolder.getPageCount()) {
			return Collections.emptyList();
		} else if (page <= pagedListHolder.getPageCount()) {
			pagedListHolder.setPage(page - 1);
		}

		return pagedListHolder.getPageList();
	}

	protected abstract void updateIfExists(T original, T desired);

}
