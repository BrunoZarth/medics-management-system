package com.brunozarth.medicsmanagement.entity;

import com.brunozarth.medicsmanagement.utils.EMedicalSpecialty;
import jakarta.persistence.*;

@Entity
public class MedicalSpecialty {

    public MedicalSpecialty(int id, EMedicalSpecialty specialty) {
        this.id = id;
        this.medicalSpecialty = specialty;
    }

    public MedicalSpecialty(){};

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "medical_specialty", nullable = false)
    private EMedicalSpecialty medicalSpecialty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EMedicalSpecialty getSpecialty() {
        return medicalSpecialty;
    }

    public void setSpecialty(EMedicalSpecialty specialty) {
        this.medicalSpecialty = specialty;
    }
}
