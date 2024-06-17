package com.apps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apps.entities.Appointment;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
