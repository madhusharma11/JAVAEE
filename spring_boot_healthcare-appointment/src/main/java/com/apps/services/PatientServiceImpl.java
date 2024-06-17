package com.apps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apps.entities.Patient;
import com.apps.repository.PatientRepository;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;
	@Override
	public Patient addNewPatient(Patient patient) {
		
		return patientRepository.save(patient);
	}



}
