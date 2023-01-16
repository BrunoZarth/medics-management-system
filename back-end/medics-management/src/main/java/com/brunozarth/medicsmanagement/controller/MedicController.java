package com.brunozarth.medicsmanagement.controller;

import com.brunozarth.medicsmanagement.entity.Medic;
import com.brunozarth.medicsmanagement.entity.MedicForm;
import com.brunozarth.medicsmanagement.entity.MedicUpdateForm;
import com.brunozarth.medicsmanagement.service.MedicService;
import com.brunozarth.medicsmanagement.utils.EMedicalSpecialty;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medic")
public class MedicController {

    private final MedicService medicService;

    public MedicController(MedicService medicService) {
        this.medicService = medicService;
    }

    // READ
    @GetMapping("/find-all")
    public ResponseEntity<List<Medic>> findAll() {
        return new ResponseEntity<>(medicService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Medic> finById(@PathVariable Long id) {
        return new ResponseEntity<>(medicService.findByIdOrThrowBadRequestException(id), HttpStatus.OK);
    }


    @GetMapping("/find-by-phone/{phone}")
    public ResponseEntity<Medic> findByPhone(@PathVariable String phone) {
        return new ResponseEntity<>(medicService.findByPhoneOrThrowBadRequestException(phone), HttpStatus.OK);
    }


    @GetMapping("/find-by-crm/{crm}")
    public ResponseEntity<Medic> findByCrm(@PathVariable String crm) {
        return new ResponseEntity<>(medicService.findByCrmOrThrowBadRequestException(crm), HttpStatus.OK);
    }


    @GetMapping("/find-by-landline/{landline}")
    public ResponseEntity<List<Medic>> findByLandline(@PathVariable String landline) {
        return new ResponseEntity<>(medicService.findByLandline(landline), HttpStatus.OK);
    }


    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<Medic>> findByName(@PathVariable String name) {
        return new ResponseEntity<>(medicService.findByName(name), HttpStatus.OK);
    }


    @GetMapping("/find-by-cep/{cep}")
    public ResponseEntity<List<Medic>> findByCep(@PathVariable String cep) {
        return new ResponseEntity<>(medicService.findByCep(cep), HttpStatus.OK);
    }


    @GetMapping("/find-by-specialty/{specialty}")
    public ResponseEntity<List<Medic>> findByMedicalSpecialty(@PathVariable EMedicalSpecialty specialty) {
        return new ResponseEntity<>(medicService.findByMedicalSpecialty(specialty), HttpStatus.OK);
    }


    // CREATE
    @PostMapping("/save")
    public ResponseEntity<Medic> saveMedic(@RequestBody @Valid MedicForm medicForm) {
        return new ResponseEntity<>(medicService.saveMedic(medicForm), HttpStatus.CREATED);
    }


    // UPDATE
    @PostMapping("/update")
    public ResponseEntity<Medic> updateMedic(@RequestBody @Valid MedicUpdateForm medicUpdateForm) {
        return new ResponseEntity<>(medicService.updateMedic(medicUpdateForm), HttpStatus.CREATED);
    }

    // DELETE
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        medicService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
