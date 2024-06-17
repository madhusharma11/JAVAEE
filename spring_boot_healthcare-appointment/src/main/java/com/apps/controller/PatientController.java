package com.apps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.entities.Patient;
import com.apps.services.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {
	@Autowired
	private PatientService patientService;

	@PostMapping("/registerPatient")
	public Patient registerPatient(@RequestBody Patient patient) {
		System.out.println("in signin dto" + patient);

		return patientService.addNewPatient(patient);

	}
}
