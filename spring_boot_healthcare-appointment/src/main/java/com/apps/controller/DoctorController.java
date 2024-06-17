package com.apps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.entities.Doctor;
import com.apps.services.DoctorService;

@RestController
@RequestMapping("/")
public class DoctorController {
	@Autowired
	private DoctorService doctorService;
	
	@PostMapping("/register")
	public Doctor registerDoctor(@RequestBody Doctor doctor)
	{	
		System.out.println("in const of "+doctor);
		return doctorService.resiterDoctor(doctor);	
	}
	@GetMapping("/login")
	public Doctor loginDoctor(@RequestBody Long id)
	{
		System.out.println("in const of "+getClass());
		return doctorService.logindoctor(id);
	}
}
