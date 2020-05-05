package com.bdpanajoto.ws.rest.controller;

import com.bdpanajoto.ws.rest.domain.Group;
import com.bdpanajoto.ws.rest.domain.Plot;
import com.bdpanajoto.ws.rest.domain.User;
import com.bdpanajoto.ws.rest.repository.ReportResult;
import com.bdpanajoto.ws.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {

    private PasswordEncoder passwordEncoder;
    private UserRepository repository;

    @Autowired
    public UserController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<User> findAll(@RequestParam Integer page, @RequestParam Integer size) {
        return repository.findAll(PageRequest.of(page, size)).getContent();
    }

    @GetMapping("/{id}/groups")
    public List<Group> findUserGroups(@PathVariable Long id, @RequestParam Integer page, @RequestParam Integer size) {
        return repository.getUser_GroupsById(id, PageRequest.of(page, size)).getContent();
    }

    @GetMapping("/{id}/plots")
    public List<Plot> findUserPlots(@PathVariable Long id, @RequestParam Integer page, @RequestParam Integer size) {
        return repository.getPlotsById(id, PageRequest.of(page, size)).getContent();
    }

    @GetMapping("/{id}")
    public User findGroup(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RestClientException("User not found"));
    }

    @PostMapping
    public User save(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/report")
    public List<ReportResult> getUserCountByYear(@RequestParam Integer page, @RequestParam Integer size) {
        return repository.getUserCountByYear(PageRequest.of(page, size)).getContent();
    }
}
