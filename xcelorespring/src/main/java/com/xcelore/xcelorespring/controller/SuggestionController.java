package com.xcelore.xcelorespring.controller;

import com.xcelore.xcelorespring.entity.*;
import com.xcelore.xcelorespring.service.DoctorService;
import com.xcelore.xcelorespring.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/suggestions")
public class SuggestionController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/suggestDoctor/{patientId}")
    public ResponseEntity<?> suggestDoctor(@PathVariable Long patientId) {
        Optional<Patient> patientOptional = patientService.getPatientById(patientId);

        if (!patientOptional.isPresent()) {
            return ResponseEntity.status(404).body("Patient not found");
        }

        Patient patient = patientOptional.get();
        City patientCity = City.valueOf(patient.getCity().toUpperCase());
        Speciality speciality = mapSymptomToSpeciality(patient.getSymptom());

        List<Doctor> doctors = doctorService.getDoctorsByCityAndSpeciality(patientCity, speciality);

        if (doctors.isEmpty()) {
            if (patientCity == null) {
                return ResponseEntity.ok("We are still waiting to expand to your location");
            } else {
                return ResponseEntity.ok("There isn’t any doctor present at your location for your symptom");
            }
        }

        return ResponseEntity.ok(doctors);
    }

    private Speciality mapSymptomToSpeciality(Symptom symptom) {
        switch (symptom) {
            case ARTHRITIS:
            case BACK_PAIN:
            case TISSUE_INJURIES:
                return Speciality.ORTHOPAEDIC;
            case DYSMENORRHEA:
                return Speciality.GYNECOLOGY;
            case SKIN_INFECTION:
            case SKIN_BURN:
                return Speciality.DERMATOLOGY;
            case EAR_PAIN:
                return Speciality.ENT;
            default:
                throw new IllegalArgumentException("Unknown symptom: " + symptom);
        }
    }
}
