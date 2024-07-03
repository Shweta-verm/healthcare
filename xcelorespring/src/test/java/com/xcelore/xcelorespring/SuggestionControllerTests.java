package com.xcelore.xcelorespring;

import com.xcelore.xcelorespring.entity.*;
import com.xcelore.xcelorespring.service.DoctorService;
import com.xcelore.xcelorespring.service.PatientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class SuggestionControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @MockBean
    private DoctorService doctorService;

    @Test
    void testSuggestDoctor() throws Exception {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("Jane Doe");
        patient.setEmail("jane.doe@example.com");
        patient.setPhone("0987654321");
        patient.setCity("Delhi");
        patient.setSymptom(Symptom.ARTHRITIS);

        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr. John Doe");
        doctor.setEmail("john.doe@example.com");
        doctor.setPhone("1234567890");
        doctor.setCity(City.DELHI);
        doctor.setSpeciality(Speciality.ORTHOPAEDIC);

        Mockito.when(patientService.getPatientById(1L)).thenReturn(Optional.of(patient));
        Mockito.when(doctorService.getDoctorsByCityAndSpeciality(City.DELHI, Speciality.ORTHOPAEDIC))
                .thenReturn(Collections.singletonList(doctor));

        mockMvc.perform(get("/suggestions/suggestDoctor/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Dr. John Doe"));
    }
}
