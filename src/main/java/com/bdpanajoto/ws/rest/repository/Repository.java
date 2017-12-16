package com.bdpanajoto.ws.rest.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<DomainType> {

	DomainType create(DomainType element);

	boolean update(Long id, DomainType updated);

	boolean delete(Long id);

	List<DomainType> findAll();

	List<DomainType> findPaginated(Integer page, Integer size);

	Optional<DomainType> findById(Long id);

}
