package com.unidunav.sluzbenik.controller;

import com.unidunav.sluzbenik.service.SluzbenikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sluzbenik")
@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'SLUZBENIK')")
public class SluzbenikController {

    @Autowired
    private SluzbenikService service;

   

}
