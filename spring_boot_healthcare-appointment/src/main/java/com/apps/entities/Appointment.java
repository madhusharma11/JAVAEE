package com.apps.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "appointments")
public class Appointment extends BaseEntity {
	@DateTimeFormat
	private LocalDate appointmentDate;
	private LocalTime appointmentTime;

	// Appointments *---->1 doctor
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "doctor_id", nullable = false)
	private Doctor doctor;

//  Appointment *---->1 Patient
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;

	public LocalDate getDate() {
		return appointmentDate;
	}

	public void setDate(LocalDate date) {
		this.appointmentDate = date;
	}

	public LocalTime getTime() {
		return appointmentTime;
	}

	public void setTime(LocalTime time) {
		this.appointmentTime = time;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
		this.doctor.getAppointments().add(this);
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(LocalTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
		this.patient.getAppointments().add(this);
	}

	@Override
	public String toString() {
		return "Appointment [date=" + appointmentDate + ", time=" + appointmentTime + "]";
	}

	public Appointment(LocalDate date, LocalTime time, Doctor doctor, Patient patient) {
		super();
		this.appointmentDate = date;
		this.appointmentTime = time;
		this.doctor = doctor;
		this.patient = patient;
	}
	public Appointment() {}


	


}
