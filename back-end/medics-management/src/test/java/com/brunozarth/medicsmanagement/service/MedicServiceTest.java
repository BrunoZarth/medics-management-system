package com.brunozarth.medicsmanagement.service;

import com.brunozarth.medicsmanagement.entity.Medic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class MedicServiceTest {

    @InjectMocks
    private MedicService medicService;

    @Mock
    private MedicRepository medicRepository;

    @Test
    void should_find_and_return_one_medic() {
        // Arrange
        final var expectedMedic = Medic.builder().name("Jimmy Olsen").age(28).build();
        when(medicRepository.findById(anyInt())).thenReturn(Optional.of(expectedMedic));

        // Act
        final var actual = medicService.findByIdOrThrowBadRequestException(getRandomInt());

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedMedic);
        verify(medicRepository, times(1)).findById(anyInt());
        verifyNoMoreInteractions(medicRepository);
    }

    private Medic getMedic(){
        MedicForm medicForm = new MedicForm();
        medicForm.setName("Keith Richards");
        medicForm.setCrm("159753964");
        medicForm.setLandline("5133374717");
        medicForm.setPhone("51955668899");
        medicForm.setCep("50000123");
        medicForm.setAdress("Exile street, 1972");

        Medic medic = new Medic();
        medic.setName(medicForm.getName());
        medic.setCrm(medicForm.getCrm);
        medic.setLandline(medicForm.getLandline);
        medic.setPhone(medicForm.getPhone);
        medic.setCep(medicForm.getCep);
        medic.setAdress(medicForm.getAdress);

        return medic;
    }

    private int getRandomInt(){
        return new Random().ints(1, 10).findFirst().getAsInt();
    }
}
