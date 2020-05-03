package com.bdpanajoto.ws.rest.repository;

import com.bdpanajoto.ws.rest.domain.Group;
import com.bdpanajoto.ws.rest.domain.Plot;
import com.bdpanajoto.ws.rest.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u.groups from User u where u.id = ?1")
    Page<Group> getUser_GroupsById(Long id, Pageable pageable);

    Page<Plot> getPlotsById(Long id, Pageable pageable);

    @Query(value = "select new com.bdpanajoto.ws.rest.repository.ReportResult(ud.age, count(ud.user)) " +
            "from UserDetails ud " +
            "group by ud.age")
    Page<ReportResult> getUserCountByYear(Pageable pageable);


}