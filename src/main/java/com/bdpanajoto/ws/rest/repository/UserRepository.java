package com.bdpanajoto.ws.rest.repository;

import com.bdpanajoto.ws.rest.domain.Group;
import com.bdpanajoto.ws.rest.domain.Plot;
import com.bdpanajoto.ws.rest.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Map;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    List<Group> getGroupsById(Long id);

    List<Plot> getPlotsById(Long id);

    @Query(value = "select distinct ud.age, count(ud.user) as cnt " +
            "from UserDetails ud " +
            "group by ud.age")
    Map<Integer, Integer> getUserCountByYear();
}