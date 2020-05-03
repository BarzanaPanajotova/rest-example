package com.bdpanajoto.ws.rest.repository;

import com.bdpanajoto.ws.rest.domain.Group;
import com.bdpanajoto.ws.rest.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {

    @Query(value = "select g.users from Group g where g.id = ?1")
    List<User> findGroup_UsersById(Long id);
}