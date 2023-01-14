package com.brunozarth.medicsmanagement.service;

import com.brunozarth.medicsmanagement.entity.Medic;
import com.brunozarth.medicsmanagement.exception.BadRequestException;
import com.brunozarth.medicsmanagement.repository.MedicRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicService {

    MedicRepository medicRepository;

    MedicService(MedicRepository medicRepository){
        this.medicRepository = medicRepository;
    }
    public Medic findByIdOrThrowBadRequestException(Long id) {
        return medicRepository.findById(id).orElseThrow(() -> new BadRequestException("sas"));
    }

    public List<Medic> findAll() {
        return medicRepository.findAll();
    }
}
