package com.brunozarth.medicsmanagement.repository;

import com.brunozarth.medicsmanagement.entity.Medic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicRepository extends JpaRepository<Medic, Long> {
}
