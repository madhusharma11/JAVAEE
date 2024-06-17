package com.apps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.dto.ApiResponse;
import com.apps.dto.AppointmentDTO;
import com.apps.services.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	@Autowired
	private AppointmentService appointmentService;
	
	public AppointmentController()
	{
		System.out.println("in const of "+getClass());	
	}
	@PostMapping("/book")
	public ApiResponse takeAppointment(@RequestBody AppointmentDTO dto)
	{
		return appointmentService.bookAppointment(dto);
		
		
	}

}
