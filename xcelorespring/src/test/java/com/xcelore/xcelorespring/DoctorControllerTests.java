package com.xcelore.xcelorespring;

import com.xcelore.xcelorespring.entity.City;
import com.xcelore.xcelorespring.entity.Doctor;
import com.xcelore.xcelorespring.entity.Speciality;
import com.xcelore.xcelorespring.service.DoctorService;
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
public class DoctorControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    @Test
    void testAddDoctor() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setName("Dr. John Doe");
        doctor.setEmail("john.doe@example.com");
        doctor.setPhone("1234567890");
        doctor.setCity(City.DELHI);
        doctor.setSpeciality(Speciality.ORTHOPAEDIC);

        Mockito.when(doctorService.addDoctor(Mockito.any(Doctor.class))).thenReturn(doctor);

        mockMvc.perform(post("/doctors")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"Dr. John Doe\", \"email\": \"john.doe@example.com\", \"phone\": \"1234567890\", \"city\": \"DELHI\", \"speciality\": \"ORTHOPAEDIC\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dr. John Doe"));
    }

    @Test
    void testRemoveDoctor() throws Exception {
        mockMvc.perform(delete("/doctors/1"))
                .andExpect(status().isNoContent());
    }
}
