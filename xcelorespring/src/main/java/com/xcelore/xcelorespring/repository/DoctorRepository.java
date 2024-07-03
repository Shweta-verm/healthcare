package com.xcelore.xcelorespring.repository;


import com.xcelore.xcelorespring.entity.City;
import com.xcelore.xcelorespring.entity.Doctor;
import com.xcelore.xcelorespring.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByCityAndSpeciality(City city, Speciality speciality);
}
