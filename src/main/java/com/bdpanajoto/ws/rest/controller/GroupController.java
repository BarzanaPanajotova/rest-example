package com.bdpanajoto.ws.rest.controller;

import com.bdpanajoto.ws.rest.domain.Group;
import com.bdpanajoto.ws.rest.domain.User;
import com.bdpanajoto.ws.rest.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.util.List;

@RestController
@RequestMapping(value = "/groups", produces = "application/json")
public class GroupController {

    private GroupRepository repository;

    @Autowired
    public GroupController(GroupRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Group> findAll(@RequestParam Integer page, @RequestParam Integer size) {
        return repository.findAll(PageRequest.of(page, size)).getContent();
    }

    @GetMapping("/{id}/users")
    public List<User> findGroupUsers(@PathVariable Long id, @RequestParam Integer page, @RequestParam Integer size) {
        return repository.findGroup_UsersById(id, PageRequest.of(page, size)).getContent();
    }

    @GetMapping("/{id}")
    public Group findGroup(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RestClientException("Group not found"));
    }

    @PostMapping
    public Group save(@RequestBody Group group) {
        return repository.save(group);
    }

    @PutMapping
    public Group update(@RequestBody Group group) {
        return repository.save(group);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
