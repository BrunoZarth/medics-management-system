package com.brunozarth.medicsmanagement.service;

import com.brunozarth.medicsmanagement.entity.Medic;
import com.brunozarth.medicsmanagement.entity.MedicForm;
import com.brunozarth.medicsmanagement.entity.MedicalSpecialty;
import com.brunozarth.medicsmanagement.repository.MedicRepository;
import com.brunozarth.medicsmanagement.utils.EMedicalSpecialty;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicServiceTest {

    @InjectMocks
    private MedicService medicService;

    @Mock
    private MedicRepository medicRepository;

    @Test
    void should_find_and_return_one_medic() {
        // Arrange
        final Medic expectedMedic = getMedic();
        when(medicRepository.findById(anyLong())).thenReturn(Optional.of(expectedMedic));

        // Act
        final Medic actual = medicService.findByIdOrThrowBadRequestException(getRandomLong());

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedMedic);
        verify(medicRepository, times(1)).findById(Long.valueOf(anyLong()));
        verifyNoMoreInteractions(medicRepository);
    }

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
        return Long.valueOf(new Random().ints(1, 10).findFirst().getAsInt());
    }
}
