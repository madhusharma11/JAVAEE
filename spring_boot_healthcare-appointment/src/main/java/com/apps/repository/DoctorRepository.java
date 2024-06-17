package com.apps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apps.entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

}
