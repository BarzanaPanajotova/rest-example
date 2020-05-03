package com.bdpanajoto.ws.rest.repository;

import com.bdpanajoto.ws.rest.domain.Group;
import com.bdpanajoto.ws.rest.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query(value = "select g.users from Group g where g.id = ?1")
    Page<User> findGroup_UsersById(Long id, Pageable pageable);
}