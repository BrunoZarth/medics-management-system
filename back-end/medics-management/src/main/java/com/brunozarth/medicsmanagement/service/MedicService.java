package com.brunozarth.medicsmanagement.service;

import com.brunozarth.medicsmanagement.entity.Medic;
import com.brunozarth.medicsmanagement.entity.MedicForm;
import com.brunozarth.medicsmanagement.entity.MedicUpdateForm;
import com.brunozarth.medicsmanagement.exception.BadRequestException;
import com.brunozarth.medicsmanagement.repository.MedicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicService implements IMedicService{

    MedicRepository medicRepository;

    MedicService(MedicRepository medicRepository){
        this.medicRepository = medicRepository;
    }
    public Medic findByIdOrThrowBadRequestException(Long id) {
        return medicRepository.findById(id).orElseThrow(() -> new BadRequestException("sas"));
    }

    @Override
    public Medic findByPhone(String phone) {
        return null;
    }

    @Override
    public Medic findByCrm(String crm) {
        return null;
    }

    public List<Medic> findAll() {
        return medicRepository.findAll();
    }

    @Override
    public List<Medic> findByLandline(String landline) {
        return null;
    }

    @Override
    public List<Medic> findByName(String name) {
        return null;
    }

    @Override
    public List<Medic> findByCep(String cep) {
        return null;
    }

    @Override
    public List<Medic> findBy(String adress) {
        return null;
    }

    @Override
    public List<Medic> findByMedicalSpecialty(String medicalSpecialty) {
        return null;
    }

    @Override
    public Medic saveMedic(MedicForm medicForm) {
        return null;
    }

    @Override
    public Medic updateMedic(MedicUpdateForm medicUpdateForm) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
