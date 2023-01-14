package com.brunozarth.medicsmanagement.service;

import com.brunozarth.medicsmanagement.entity.Medic;
import com.brunozarth.medicsmanagement.entity.MedicForm;
import com.brunozarth.medicsmanagement.entity.MedicUpdateForm;

import java.util.List;

public interface IMedicService {

    // READ
    Medic findByIdOrThrowBadRequestException(Long id);

    Medic findByPhone(String phone);

    Medic findByCrm(String crm);

    List<Medic> findAll();

    List<Medic> findByLandline(String landline);

    List<Medic> findByName(String name);

    List<Medic> findByCep(String cep);

    List<Medic> findBy(String adress);

    List<Medic> findByMedicalSpecialty(String medicalSpecialty);

    // CREATE
    Medic saveMedic(MedicForm medicForm);

    // UPDATE
    Medic updateMedic(MedicUpdateForm medicUpdateForm);

    // DELETE
    void deleteById(Long id);

}
