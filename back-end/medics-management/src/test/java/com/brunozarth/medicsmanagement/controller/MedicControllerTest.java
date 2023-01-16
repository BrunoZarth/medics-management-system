package com.brunozarth.medicsmanagement.controller;

import com.brunozarth.medicsmanagement.entity.Medic;
import com.brunozarth.medicsmanagement.entity.MedicForm;
import com.brunozarth.medicsmanagement.repository.MedicRepository;
import com.brunozarth.medicsmanagement.utils.EMedicalSpecialty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MedicControllerTest {
    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @Order(1)
    void should_save_medic_to_use_in_tests() throws Exception {
            ObjectMapper objectMapper = new ObjectMapper();
            Medic medic = getMedic();
            String medicStringJSON = objectMapper.writeValueAsString(medic);

            this.mockMvc.perform(post("/medic/save")
                    .contentType(APPLICATION_JSON)
                    .content(medicStringJSON))
                    .andDo(print())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.name").value("Keith Richards"))
                    .andExpect(jsonPath("$.crm").value("1597539"))
                    .andExpect(jsonPath("$.landline").value("5133374717"))
                    .andExpect(jsonPath("$.phone").value("51955668899"))
                    .andExpect(jsonPath("$.cep").value("50000123"))
                    .andExpect(jsonPath("$.adress").value("Exile street, 1972"));

            assertThat(this.medicRepository.findAll()).hasSize(1);
    }

    // READ
    @Test
    @Order(2)
    void should_retrieve_all_medics() throws Exception {
        this.mockMvc.perform(get("/medic/find-all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @Order(2)
    void should_find_by_id_and_retrieve_one_medic() throws Exception {
        this.mockMvc.perform(get("/medic/find-by-id/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Keith Richards"))
                .andExpect(jsonPath("$.crm").value("1597539"))
                .andExpect(jsonPath("$.landline").value("5133374717"))
                .andExpect(jsonPath("$.phone").value("51955668899"))
                .andExpect(jsonPath("$.cep").value("50000123"))
                .andExpect(jsonPath("$.adress").value("Exile street, 1972"));
    }

    @Test
    @Order(2)
    void should_find_by_phone_and_retrieve_one_medic() throws Exception {
        this.mockMvc.perform(get("/medic/find-by-phone/{phone}", "51955668899"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Keith Richards"))
                .andExpect(jsonPath("$.crm").value("1597539"))
                .andExpect(jsonPath("$.landline").value("5133374717"))
                .andExpect(jsonPath("$.phone").value("51955668899"))
                .andExpect(jsonPath("$.cep").value("50000123"))
                .andExpect(jsonPath("$.adress").value("Exile street, 1972"));
    }
    @Test
    @Order(2)
    void should_find_by_crm_and_retrieve_one_medic() throws Exception {
        this.mockMvc.perform(get("/medic/find-by-crm/{crm}", "1597539"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Keith Richards"))
                .andExpect(jsonPath("$.crm").value("1597539"))
                .andExpect(jsonPath("$.landline").value("5133374717"))
                .andExpect(jsonPath("$.phone").value("51955668899"))
                .andExpect(jsonPath("$.cep").value("50000123"))
                .andExpect(jsonPath("$.adress").value("Exile street, 1972"));
    }
    @Test
    @Order(2)
    void should_find_by_landline_and_retrieve_medic_list() throws Exception {
        this.mockMvc.perform(get("/medic/find-by-landline/{landline}", "5133374717"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].id").value(1));
    }
    @Test
    @Order(2)
    void should_find_by_name_and_retrieve_medic_list() throws Exception {
        this.mockMvc.perform(get("/medic/find-by-name/{name}", "Keith Richards"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].id").value(1));
    }
    @Test
    @Order(2)
    void should_find_by_cep_and_retrieve_medic_list() throws Exception {
        this.mockMvc.perform(get("/medic/find-by-cep/{cep}", "50000123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].id").value(1));
    }
    @Test
    @Order(2)
    void should_find_by_medical_specialty_and_retrieve_medic_list() throws Exception {
        this.mockMvc.perform(get("/medic/find-by-specialty/{specialty}", "ANGIOLOGIA"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].id").value(1));
    }
    @Test
    @Order(2)
    void should_not_found_a_medic_that_doesnt_exists() throws Exception {
        this.mockMvc.perform(get("/medic/find-by-id/{id}", 20))
                .andDo(print())
                .andExpect(status().is(400));
    }

    // CREATE
    @Test
    @Order(2)
    void should_try_to_save_another_medic_with_same_crm_then_throw_RuntimeException() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Medic medic = getMedic();
        String medicStringJSON = objectMapper.writeValueAsString(medic);

        Assertions.assertThrows(Exception.class, () ->
                        this.mockMvc.perform(post("/medic/save")
                                .contentType(APPLICATION_JSON)
                                .content(medicStringJSON)));

        assertThat(this.medicRepository.findAll()).hasSize(1);
    }

    @Test
    @Order(2)
    void should_try_to_save_medic_with_name_length_greater_than_120_and_fail() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Medic medic = getMedic();
        medic.setCrm(getRandomAlphanumericString(7));
        medic.setName(getRandomAlphabeticString(121));
        String medicStringJSON = objectMapper.writeValueAsString(medic);

        this.mockMvc.perform(post("/medic/save")
                .contentType(APPLICATION_JSON)
                .content(medicStringJSON))
                .andExpect(status().is(400));

        assertThat(this.medicRepository.findAll()).hasSize(1);
    }

    @Test
    @Order(2)
    void should_try_to_save_medic_with_crm_length_greater_than_7_and_fail() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Medic medic = getMedic();
        medic.setCrm(getRandomAlphanumericString(8));
        String medicStringJSON = objectMapper.writeValueAsString(medic);

        this.mockMvc.perform(post("/medic/save")
                        .contentType(APPLICATION_JSON)
                        .content(medicStringJSON))
                .andExpect(status().is(400));

        assertThat(this.medicRepository.findAll()).hasSize(1);
    }

    @Test
    @Order(2)
    void should_try_to_save_medic_with_phone_not_number_digits_and_fail() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Medic medic = getMedic();
        medic.setCrm(getRandomAlphanumericString(7));
        medic.setPhone(getRandomAlphabeticString(3));
        String medicStringJSON = objectMapper.writeValueAsString(medic);

        this.mockMvc.perform(post("/medic/save")
                        .contentType(APPLICATION_JSON)
                        .content(medicStringJSON))
                .andExpect(status().is(400));

        assertThat(this.medicRepository.findAll()).hasSize(1);
    }

    @Test
    @Order(2)
    void should_try_to_save_medic_with_landline_not_number_digits_and_fail() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Medic medic = getMedic();
        medic.setCrm(getRandomAlphanumericString(7));
        medic.setLandline(getRandomAlphabeticString(3));
        String medicStringJSON = objectMapper.writeValueAsString(medic);

        this.mockMvc.perform(post("/medic/save")
                        .contentType(APPLICATION_JSON)
                        .content(medicStringJSON))
                .andExpect(status().is(400));

        assertThat(this.medicRepository.findAll()).hasSize(1);
    }

    @Test
    @Order(2)
    void should_try_to_save_medic_with_cep_not_number_digits_and_fail() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Medic medic = getMedic();
        medic.setCrm(getRandomAlphanumericString(7));
        medic.setCep(getRandomAlphabeticString(3));
        String medicStringJSON = objectMapper.writeValueAsString(medic);

        this.mockMvc.perform(post("/medic/save")
                        .contentType(APPLICATION_JSON)
                        .content(medicStringJSON))
                .andExpect(status().is(400));

        assertThat(this.medicRepository.findAll()).hasSize(1);
    }

    @Test
    @Order(2)
    void should_try_to_save_medic_with_specialty_list_length_lesser_than_2_and_fail() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Medic medic = getMedic();
        medic.setCrm(getRandomAlphanumericString(7));
        medic.setMedicalSpecialty(List.of(EMedicalSpecialty.BUCO_MAXILO));
        String medicStringJSON = objectMapper.writeValueAsString(medic);

        this.mockMvc.perform(post("/medic/save")
                        .contentType(APPLICATION_JSON)
                        .content(medicStringJSON))
                .andExpect(status().is(400));

        assertThat(this.medicRepository.findAll()).hasSize(1);
    }

    // UPDATE
    @Test
    @Order(3)
    void should_update_one_medic() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Medic medic = getMedic();
        medic.setName("Lemmy Kilmister");
        medic.setCrm("1597539");
        medic.setLandline("33256987");
        medic.setPhone("59648755532");
        medic.setCep("95555000");
        medic.setAdress("Sunset Strip, 77");
        medic.setMedicalSpecialty(List.of(EMedicalSpecialty.CARDIOLOGIA_CLÍNCA, EMedicalSpecialty.CIRURGIA_CABEÇA_E_PESCOÇO));

        String medicStringJSON = objectMapper.writeValueAsString(medic);

        this.mockMvc.perform(post("/medic/update")
                        .contentType(APPLICATION_JSON)
                        .content(medicStringJSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Lemmy Kilmister"))
                .andExpect(jsonPath("$.crm").value("1597539"))
                .andExpect(jsonPath("$.landline").value("33256987"))
                .andExpect(jsonPath("$.phone").value("59648755532"))
                .andExpect(jsonPath("$.cep").value("95555000"))
                .andExpect(jsonPath("$.adress").value("Sunset Strip, 77"));

        assertThat(this.medicRepository.findAll()).hasSize(1);
    }

    // DELETE
    @Test
    @Order(4)
    void should_delete_one_medic() throws Exception {

        this.mockMvc.perform(delete("/medic/delete-by-id/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertThat(this.medicRepository.findAll()).hasSize(0);
    }


    private Medic getMedic(){
        MedicForm medicForm = new MedicForm();
        medicForm.setName("Keith Richards");
        medicForm.setCrm("1597539");
        medicForm.setLandline("5133374717");
        medicForm.setPhone("51955668899");
        medicForm.setCep("50000123");
        medicForm.setAdress("Exile street, 1972");
        medicForm.setMedicalSpecialty(List.of(EMedicalSpecialty.ANGIOLOGIA, EMedicalSpecialty.ALERGOLOGIA));

        Medic medic = new Medic();
        medic.setName(medicForm.getName());
        medic.setCrm(medicForm.getCrm());
        medic.setLandline(medicForm.getLandline());
        medic.setPhone(medicForm.getPhone());
        medic.setCep(medicForm.getCep());
        medic.setAdress(medicForm.getAdress());
        medic.setMedicalSpecialty(medicForm.getMedicalSpecialty());

        return medic;
    }

    private String getRandomAlphabeticString(int stringLength){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(stringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    private String getRandomAlphanumericString(int stringLength){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(stringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
