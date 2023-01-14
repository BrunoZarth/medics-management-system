package com.brunozarth.medicsmanagement.service;

import com.brunozarth.medicsmanagement.entity.Medic;
import com.brunozarth.medicsmanagement.entity.MedicForm;
import com.brunozarth.medicsmanagement.entity.MedicUpdateForm;
import com.brunozarth.medicsmanagement.entity.MedicalSpecialty;
import com.brunozarth.medicsmanagement.exception.BadRequestException;
import com.brunozarth.medicsmanagement.repository.MedicRepository;
import com.brunozarth.medicsmanagement.utils.EMedicalSpecialty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MedicServiceTest {

    @InjectMocks
    private MedicService medicService;

    @Mock
    private MedicRepository medicRepository;

    // READ
    @Test
    void should_find_by_id_and_return_one_medic() {
        // Arrange
        final Medic expectedMedic = getMedic();
        when(medicRepository.findById(anyLong())).thenReturn(Optional.of(expectedMedic));

        // Act
        final Medic actual = medicService.findByIdOrThrowBadRequestException(getRandomLong());

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedMedic);
        verify(medicRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(medicRepository);
    }

    @Test
    void should_not_found_a_medic_by_id_that_doesnt_exists() {
        // Arrange
        when(medicRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(BadRequestException.class, () -> medicService.findByIdOrThrowBadRequestException(getRandomLong()));
        verify(medicRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(medicRepository);
    }

    @Test
    void should_find_by_phone_and_return_one_medic(){
        // Arrange
        final Medic expectedMedic = getMedic();
        when(medicRepository.findByPhone(anyString())).thenReturn(Optional.of(expectedMedic));

        // Act
        final Medic actual = medicService.findByPhoneOrThrowBadRequestException(getRandomAlphanumericString(4));

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedMedic);
        verify(medicRepository, times(1)).findByPhone(anyString());
        verifyNoMoreInteractions(medicRepository);
    }

    @Test
    void should_not_found_a_medic_by_phone_that_doesnt_exists() {
        // Arrange
        when(medicRepository.findByPhone(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(BadRequestException.class, () -> medicService.findByPhoneOrThrowBadRequestException(anyString()));
        verify(medicRepository, times(1)).findByPhone(anyString());
        verifyNoMoreInteractions(medicRepository);
    }

    @Test
    void should_find_by_crm_and_return_one_medic(){
        final Medic expectedMedic = getMedic();
        when(medicRepository.findByCrm(anyString())).thenReturn(Optional.of(expectedMedic));

        // Act
        final Medic actual = medicService.findByCrmOrThrowBadRequestException(getRandomAlphanumericString(4));

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedMedic);
        verify(medicRepository, times(1)).findByCrm(anyString());
        verifyNoMoreInteractions(medicRepository);
    }

    @Test
    void should_not_found_a_medic_by_crm_that_doesnt_exists() {
        // Arrange
        when(medicRepository.findByCrm(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(BadRequestException.class, () -> medicService.findByCrmOrThrowBadRequestException(anyString()));
        verify(medicRepository, times(1)).findByCrm(anyString());
        verifyNoMoreInteractions(medicRepository);
    }
    @Test
    void should_find_and_return_all_medics() {
        // Arrange
        when(medicRepository.findAll()).thenReturn(List.of(new Medic(), new Medic()));

        // Act & Assert
        assertThat(medicService.findAll().size()).isEqualTo(2);
        verify(medicRepository, times(1)).findAll();
        verifyNoMoreInteractions(medicRepository);
    }
    @Test
    void should_find_by_landline_and_return_medic_list(){
        // Arrange
        when(medicRepository.findByLandline(anyString())).thenReturn(List.of(new Medic(), new Medic()));

        // Act & Assert
        assertThat(medicService.findByLandline(anyString()).size()).isEqualTo(2);
        verify(medicRepository, times(1)).findByLandline(anyString());
        verifyNoMoreInteractions(medicRepository);
    }
    @Test
    void should_find_by_name_and_return_medic_list() {
        // Arrange
        when(medicRepository.findByName(anyString())).thenReturn(List.of(new Medic(), new Medic()));

        // Act & Assert
        assertThat(medicService.findByName(anyString()).size()).isEqualTo(2);
        verify(medicRepository, times(1)).findByName(anyString());
        verifyNoMoreInteractions(medicRepository);
    }
    @Test
    void should_find_by_cep_and_return_medic_list(){
        // Arrange
        when(medicRepository.findByCep(anyString())).thenReturn(List.of(new Medic(), new Medic()));

        // Act & Assert
        assertThat(medicService.findByCep(anyString()).size()).isEqualTo(2);
        verify(medicRepository, times(1)).findByCep(anyString());
        verifyNoMoreInteractions(medicRepository);
    }
    @Test
    void should_find_by_medical_specialty_and_return_medic_list(){
        // Arrange
        when(medicRepository.findByMedicalSpecialtyMedicalSpecialty(anyString())).thenReturn(List.of(new Medic(), new Medic()));

        // Act & Assert
        assertThat(medicService.findByMedicalSpecialtyMedicalSpecialty(anyString()).size()).isEqualTo(2);
        verify(medicRepository, times(1)).findByMedicalSpecialtyMedicalSpecialty(anyString());
        verifyNoMoreInteractions(medicRepository);
    }


    // CREATE
    @Test
    void should_save_one_medic() {
        // Arrange
        final Medic medicToSave = getMedic();
        when(medicRepository.save(any(Medic.class))).thenReturn(medicToSave);

        // Act
        final Medic actual = medicService.saveMedic(new MedicForm());

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(medicToSave);
        verify(medicRepository, times(1)).save(any(Medic.class));
        verifyNoMoreInteractions(medicRepository);
    }

    // UPDATE
    @Test
    void should_try_to_update_one_medic_then_throw_BadRequestException() {
            Assertions.assertThrows(BadRequestException.class, () -> medicService.updateMedic(new MedicUpdateForm()));
    }

    // DELETE
    @Test
    void should_delete_one_medic() {
        // Arrange
        doNothing().when(medicRepository).deleteById(anyLong());

        // Act & Assert
        medicService.deleteById(getRandomLong());
        verify(medicRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(medicRepository);
    }

    // UTILS
    private Medic getMedic(){
        MedicalSpecialty medicalSpecialty1 = new MedicalSpecialty(1, EMedicalSpecialty.ANGIOLOGIA);
        MedicalSpecialty medicalSpecialty2 = new MedicalSpecialty(2, EMedicalSpecialty.ALERGOLOGIA);
        List<MedicalSpecialty> medicalSpecialtyList = new ArrayList<>();
        medicalSpecialtyList.add(medicalSpecialty1);
        medicalSpecialtyList.add(medicalSpecialty2);

        MedicForm medicForm = new MedicForm();
        medicForm.setName("Keith Richards");
        medicForm.setCrm("159753964");
        medicForm.setLandline("5133374717");
        medicForm.setPhone("51955668899");
        medicForm.setCep("50000123");
        medicForm.setAdress("Exile street, 1972");
        medicForm.setMedicalSpecialty(medicalSpecialtyList);

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

    private Long getRandomLong(){
        Long longValue = Long.valueOf(new Random().ints(1, 10).findFirst().getAsInt());
        return longValue;
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
