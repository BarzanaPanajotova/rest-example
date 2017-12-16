package com.bdpanajoto.ws.rest.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bdpanajoto.ws.rest.repository.Repository;
import com.bdpanajoto.ws.rest.resource.ResourceAssembler;

public abstract class AbstractController<DomainType, ResourceType> {

	protected Repository<DomainType> repository;
	protected ResourceAssembler<DomainType, ResourceType> assembler;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<ResourceType>> findAll() {
		List<DomainType> elements = repository.findAll();

		return new ResponseEntity<>(assembler.toResourceCollection(elements), HttpStatus.OK);
	}

	@RequestMapping(params = { "page", "size" }, method = RequestMethod.GET)
	public ResponseEntity<Collection<ResourceType>> findPaginated(@RequestParam("page") Integer page,
			@RequestParam("size") Integer size) {
		List<DomainType> elements = repository.findPaginated(page, size);

		return new ResponseEntity<>(assembler.toResourceCollection(elements), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ResourceType> create(@RequestBody DomainType element) {
		DomainType createdElement = repository.create(element);
		return new ResponseEntity<>(assembler.toResource(createdElement), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResourceType> findById(@PathVariable Long id) {
		Optional<DomainType> element = repository.findById(id);

		if (element.isPresent()) {
			return new ResponseEntity<>(assembler.toResource(element.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		boolean wasDeleted = repository.delete(id);
		HttpStatus responseStatus = wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(responseStatus);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<ResourceType> update(@PathVariable Long id, @RequestBody DomainType updated) {
		boolean wasUpdated = repository.update(id, updated);

		if (wasUpdated) {
			return findById(id);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
