package com.brunozarth.medicsmanagement.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Medic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    String name; //máximo 120 caractéres

    int CRM; // máximo 7 caracteres

    String landline; // somente números

    String phone; // somente números

    String cep; // somente números (Ao cadastrar o CEP, deve ser feita uma reqisição via XHR para a API dos correios e retornar todos os dados de endereço do cliente).

    String adress;

    @OneToMany
    List<MedicalSpecialty> medicalSpecialty; // mínimo de duas especialidades

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
