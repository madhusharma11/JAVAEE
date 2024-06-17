package com.apps.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apps.custom_excpetions.InvalidCredentialsException;
import com.apps.dto.ApiResponse;
import com.apps.dto.AppointmentDTO;
import com.apps.entities.Appointment;
import com.apps.entities.Doctor;
import com.apps.entities.Patient;
import com.apps.repository.AppointmentRepository;
import com.apps.repository.DoctorRepository;
import com.apps.repository.PatientRepository;
@Service
@Transactional

public class AppointmentServiceImpl implements AppointmentService{

	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public ApiResponse bookAppointment(AppointmentDTO newAppointment) {
		Patient  patient=patientRepository.findByEmail(newAppointment.getEmail())
				.orElseThrow(()->new InvalidCredentialsException("Invalid email!!!"));
		
		Doctor doctor=doctorRepository.findById(newAppointment.getDoctor_id())
				.orElseThrow(() -> new InvalidCredentialsException("Invalid blogger Doctor id !!!!"));
		
		
		System.out.println("\n before "+newAppointment);
	Appointment appointment=mapper.map(newAppointment,Appointment.class);
	System.out.println("\n after "+appointment);
	appointment.setDoctor(doctor);
	appointment.setPatient(patient);
		System.out.println("appointment"+appointment);
	Appointment savedAppointment=appointmentRepository.save(appointment);
		
		 return new ApiResponse("appointment booked!!!"+savedAppointment.getId()); 
	}

}
