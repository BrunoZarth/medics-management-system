package com.brunozarth.medicsmanagement.service;

import com.brunozarth.medicsmanagement.entity.Medic;
import com.brunozarth.medicsmanagement.entity.MedicForm;
import com.brunozarth.medicsmanagement.entity.MedicUpdateForm;
import com.brunozarth.medicsmanagement.utils.EMedicalSpecialty;

import java.util.List;

public interface IMedicService {

    // READ
    Medic findByIdOrThrowBadRequestException(Long id);

    Medic findByPhoneOrThrowBadRequestException(String phone);

    Medic findByCrmOrThrowBadRequestException(String crm);

    List<Medic> findAll();

    List<Medic> findByLandline(String landline);

    List<Medic> findByName(String name);

    List<Medic> findByCep(String cep);

    //List<Medic> findByAdress(String adress);

    //List<Medic> findByMedicalSpecialtyList(List<String> medicalSpecialty);

    List<Medic> findByMedicalSpecialty(EMedicalSpecialty medicalSpecialty);

    // CREATE
    Medic saveMedic(MedicForm medicForm);

    // UPDATE
    Medic updateMedic(MedicUpdateForm medicUpdateForm);

    // DELETE
    void deleteById(Long id);


}
