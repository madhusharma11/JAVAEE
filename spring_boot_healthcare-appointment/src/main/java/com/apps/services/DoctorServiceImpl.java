package com.apps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apps.entities.Doctor;
import com.apps.repository.DoctorRepository;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService{
@Autowired
private DoctorRepository doctorRepository;
		@Override
	public Doctor resiterDoctor(Doctor doctor) {
			return doctorRepository.save(doctor);
		
	}
		
		@Override
		public Doctor logindoctor(Long id) {
			
			return null;
		}

}
