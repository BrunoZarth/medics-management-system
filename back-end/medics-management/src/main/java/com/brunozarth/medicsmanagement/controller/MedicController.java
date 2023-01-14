package com.brunozarth.medicsmanagement.controller;

import com.brunozarth.medicsmanagement.entity.Medic;
import com.brunozarth.medicsmanagement.service.MedicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medic")
public class MedicController {

    private final MedicService medicService;

    public MedicController(MedicService medicService){
        this.medicService = medicService;
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Medic>> findAll(){
        return new ResponseEntity<>(medicService.findAll(), HttpStatus.OK);
    }
}
