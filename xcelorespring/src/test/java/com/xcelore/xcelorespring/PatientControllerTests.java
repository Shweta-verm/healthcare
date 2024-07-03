package com.xcelore.xcelorespring;

import com.xcelore.xcelorespring.entity.Patient;
import com.xcelore.xcelorespring.entity.Symptom;
import com.xcelore.xcelorespring.service.PatientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class PatientControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Test
    void testAddPatient() throws Exception {
        Patient patient = new Patient();
        patient.setName("Jane Doe");
        patient.setEmail("jane.doe@example.com");
        patient.setPhone("0987654321");
        patient.setCity("Delhi");
        patient.setSymptom(Symptom.ARTHRITIS);

        Mockito.when(patientService.addPatient(Mockito.any(Patient.class))).thenReturn(patient);

        mockMvc.perform(post("/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"Jane Doe\", \"email\": \"jane.doe@example.com\", \"phone\": \"0987654321\", \"city\": \"Delhi\", \"symptom\": \"ARTHRITIS\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"));
    }

    @Test
    void testRemovePatient() throws Exception {
        mockMvc.perform(delete("/patients/1"))
                .andExpect(status().isNoContent());
    }
}
