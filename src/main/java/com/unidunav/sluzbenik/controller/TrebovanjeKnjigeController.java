package com.unidunav.sluzbenik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unidunav.sluzbenik.dto.TrebovanjeKnjigeDTO;
import com.unidunav.sluzbenik.model.TrebovanjeKnjige;
import com.unidunav.sluzbenik.service.TrebovanjeKnjigeService;

@RestController
@RequestMapping("/api/trebovanja-knjige")
@CrossOrigin(origins = "*")
public class TrebovanjeKnjigeController {

    @Autowired
    private TrebovanjeKnjigeService service;

    @GetMapping
    public List<TrebovanjeKnjige> getAll() {
        return service.findAll();
    }

    @PostMapping
    public TrebovanjeKnjige create(@RequestBody TrebovanjeKnjigeDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public TrebovanjeKnjige update(@PathVariable Long id, @RequestBody TrebovanjeKnjigeDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}