package com.xcelore.xcelorespring.repository;


import com.xcelore.xcelorespring.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {}
