package com.bdpanajoto.ws.rest.controller;

import com.bdpanajoto.ws.rest.domain.Group;
import com.bdpanajoto.ws.rest.domain.Plot;
import com.bdpanajoto.ws.rest.domain.User;
import com.bdpanajoto.ws.rest.repository.ReportResult;
import com.bdpanajoto.ws.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {

    private UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}/groups")
    public Collection<Group> findUserGroups(@PathVariable Long id) {
        return repository.getUser_GroupsById(id);
    }

    @GetMapping("/{id}/plots")
    public Collection<Plot> findUserPlots(@PathVariable Long id) {
        return repository.getPlotsById(id);
    }

    @GetMapping
    public Iterable<User> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public User findGroup(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RestClientException("User not found"));
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return repository.save(user);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return repository.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/report")
    public List<ReportResult> getUserCountByYear() {
        return repository.getUserCountByYear();
    }
}
