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
        return medicRepository.findById(id).orElseThrow(() -> new BadRequestException("Medic ID not found!"));
    }

    @Override
    public Medic findByPhoneOrThrowBadRequestException(String phone) {
        return medicRepository.findByPhone(phone).orElseThrow(() -> new BadRequestException("Medic Phone not found!"));
    }

    @Override
    public Medic findByCrmOrThrowBadRequestException(String crm) {
        return medicRepository.findByCrm(crm).orElseThrow(() -> new BadRequestException("Medic CRM not found!"));
    }

    public List<Medic> findAll() {
        return medicRepository.findAll();
    }

    @Override
    public List<Medic> findByLandline(String landline) {
        return medicRepository.findByLandline(landline);
    }

    @Override
    public List<Medic> findByName(String name) {
        return medicRepository.findByName(name);
    }

    @Override
    public List<Medic> findByCep(String cep) {
        return medicRepository.findByCep(cep);
    }

    @Override
    public List<Medic> findByMedicalSpecialtyMedicalSpecialty(String medicalSpecialty) {
        return medicRepository.findByMedicalSpecialtyMedicalSpecialty(medicalSpecialty);
    }

    @Override
    public Medic saveMedic(MedicForm medicForm) {

        Medic medic = new Medic();

        medic.setName(medicForm.getName());
        medic.setCrm(medicForm.getCrm());
        medic.setLandline(medicForm.getLandline());
        medic.setPhone(medicForm.getPhone());
        medic.setCep(medicForm.getCep());
        medic.setAdress(medicForm.getAdress());
        medic.setMedicalSpecialty(medicForm.getMedicalSpecialty());

        return medicRepository.save(medic);
    }

    @Override
    public Medic updateMedic(MedicUpdateForm medicUpdateForm) {

        Medic medic = this.findByCrmOrThrowBadRequestException(medicUpdateForm.getCrm()); // CRM is always unique

        medic.setName(medicUpdateForm.getName());
        medic.setCrm(medicUpdateForm.getCrm());
        medic.setLandline(medicUpdateForm.getLandline());
        medic.setPhone(medicUpdateForm.getPhone());
        medic.setCep(medicUpdateForm.getCep());
        medic.setAdress(medicUpdateForm.getAdress());
        medic.setMedicalSpecialty(medicUpdateForm.getMedicalSpecialty());

        return medicRepository.save(medic);
    }

    @Override
    public void deleteById(Long id) {
        medicRepository.deleteById(id);
    }
}
