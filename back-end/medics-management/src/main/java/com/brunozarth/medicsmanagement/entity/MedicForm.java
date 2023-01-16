package com.brunozarth.medicsmanagement.entity;

import com.brunozarth.medicsmanagement.utils.EMedicalSpecialty;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;

import java.util.List;

public class MedicForm {

    public MedicForm(String name, String crm, String landline, String phone, String cep, String adress, List<EMedicalSpecialty> medicalSpecialty) {
        this.name = name;
        this.crm = crm;
        this.landline = landline;
        this.phone = phone;
        this.cep = cep;
        this.adress = adress;
        this.medicalSpecialty = medicalSpecialty;
    }

    public MedicForm() {
    }

    @Size(max = 120, message = "Name digits must be less than 120!")
    String name; //máximo 120 caractéres

    @NotNull(message = "CRM cannot be null!")
    @Pattern(regexp = "^[0-9]*$", message = "CRM must have onlynumbers!")
    @Size(max = 7, message = "CRM digits must be less than 7!")
    String crm; // somente números máximo 7 caracteres

    @Pattern(regexp = "^[0-9]*$", message = "Landline must have only numbers!")
    String landline; // somente números

    @Pattern(regexp = "^[0-9]*$", message = "Phone must have only numbers!")
    String phone; // somente números

    @NotNull(message = "CEP cannot be null")
    @Pattern(regexp = "^[0-9]*$", message = "CEP must have only numbers!")
    String cep; // somente números (Ao cadastrar o CEP, deve ser feita uma reqisição via XHR para a API dos correios e retornar todos os dados de endereço do cliente).

    String adress;

    @Size(min = 2, message = "Must have at last 2 Medical Specialty!")
    List<EMedicalSpecialty> medicalSpecialty; // mínimo de duas especialidades



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
