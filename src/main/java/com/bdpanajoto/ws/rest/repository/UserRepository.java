package com.bdpanajoto.ws.rest.repository;

import com.bdpanajoto.ws.rest.domain.Group;
import com.bdpanajoto.ws.rest.domain.Plot;
import com.bdpanajoto.ws.rest.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Query(value = "select u.groups from User u where u.id = ?1")
    List<Group> getUser_GroupsById(Long id);

    List<Plot> getPlotsById(Long id);

    @Query(value = "select new com.bdpanajoto.ws.rest.repository.ReportResult(ud.age, count(ud.user)) " +
            "from UserDetails ud " +
            "group by ud.age")
    List<ReportResult> getUserCountByYear();


}