package com.apps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apps.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	Optional<Patient> findByEmail(String email);
	
}
