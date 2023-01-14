package com.brunozarth.medicsmanagement.repository;

import com.brunozarth.medicsmanagement.entity.Medic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicRepository extends JpaRepository<Medic, Long> {
    Optional<Medic> findByPhone(String phone);

    Optional<Medic> findByCrm(String crm);

    List<Medic> findByLandline(String landLine);

    List<Medic>  findByName(String name);

    List<Medic>  findByCep(String cep);

    // List<Medic>  findByAdress(String adress); // Useless since we have findByCep method.

    List<Medic>  findByMedicalSpecialtyMedicalSpecialty(String medicalSpecialty);
}
