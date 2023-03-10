package com.brunozarth.medicsmanagement.entity;

import com.brunozarth.medicsmanagement.utils.EMedicalSpecialty;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Medic {

    public Medic(Long id, String name, String crm, String landline, String phone, String cep, String adress, List<EMedicalSpecialty> medicalSpecialty) {
        this.id = id;
        this.name = name;
        this.crm = crm;
        this.landline = landline;
        this.phone = phone;
        this.cep = cep;
        this.adress = adress;
        this.medicalSpecialty = medicalSpecialty;
    }

    public Medic(){};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    String name; //máximo 120 caractéres

    String crm; // somente números máximo 7 caracteres

    String landline; // somente números

    String phone; // somente números

    String cep; // somente números (Ao cadastrar o CEP, deve ser feita uma reqisição via XHR para a API dos correios e retornar todos os dados de endereço do cliente).

    String adress;


    @ElementCollection
    List<EMedicalSpecialty> medicalSpecialty; // mínimo de duas especialidades

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public List<EMedicalSpecialty> getMedicalSpecialty() {
        return medicalSpecialty;
    }

    public void setMedicalSpecialty(List<EMedicalSpecialty> medicalSpecialty) {
        this.medicalSpecialty = medicalSpecialty;
    }
}
