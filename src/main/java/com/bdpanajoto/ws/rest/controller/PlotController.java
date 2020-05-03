package com.bdpanajoto.ws.rest.controller;

import com.bdpanajoto.ws.rest.domain.Plot;
import com.bdpanajoto.ws.rest.repository.PlotRepository;
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

@RestController
@RequestMapping(value = "/plots", produces = "application/json")
public class PlotController {

    private PlotRepository repository;

    @Autowired
    public PlotController(PlotRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Plot> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Plot findGroup(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RestClientException("Plot not found"));
    }

    @PostMapping
    public Plot save(@RequestBody Plot plot) {
        return repository.save(plot);
    }

    @PutMapping
    public Plot update(@RequestBody Plot plot) {
        return repository.save(plot);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
