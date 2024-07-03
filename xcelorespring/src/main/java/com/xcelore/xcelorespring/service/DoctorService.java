package com.xcelore.xcelorespring.service;

import com.xcelore.xcelorespring.entity.City;
import com.xcelore.xcelorespring.entity.Doctor;
import com.xcelore.xcelorespring.entity.Speciality;
import com.xcelore.xcelorespring.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void removeDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    public List<Doctor> getDoctorsByCityAndSpeciality(City city, Speciality speciality) {
        return doctorRepository.findByCityAndSpeciality(city, speciality);
    }
}
