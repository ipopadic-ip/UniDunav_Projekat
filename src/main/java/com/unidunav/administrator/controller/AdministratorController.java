package com.unidunav.administrator.controller;

import com.unidunav.administrator.dto.AdministratorDTO;
import com.unidunav.administrator.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administratori")
@CrossOrigin(origins = "*")
public class AdministratorController {

    @Autowired
    private AdministratorService service;

    @PostMapping
    public AdministratorDTO create(@RequestBody AdministratorDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<AdministratorDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public AdministratorDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public AdministratorDTO update(@PathVariable Long id, @RequestBody AdministratorDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}