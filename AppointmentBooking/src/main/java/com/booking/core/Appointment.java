package com.booking.core;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    private int id;
    private int doctorId;
    private LocalDate date;
    private LocalTime time;
    private String patientName;
    private String address;
    private String bloodGroup;

    // Constructors
    public Appointment() {
    }

    public Appointment( int doctorId, LocalDate date, LocalTime time, String patientName, String address, String bloodGroup) {
       
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.patientName = patientName;
        this.address = address;
        this.bloodGroup = bloodGroup;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    // toString method
    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", doctorId=" + doctorId +
                ", date=" + date +
                ", time=" + time +
                ", patientName='" + patientName + '\'' +
                ", address='" + address + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                '}';
    }
}
