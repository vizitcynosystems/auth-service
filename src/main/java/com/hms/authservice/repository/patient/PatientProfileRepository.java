package com.hms.authservice.repository.patient;

import com.hms.authservice.models.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientProfileRepository extends JpaRepository<PatientProfile , UUID> {

    PatientProfile findByPhno(String phno);

    PatientProfile findByUhid(String phno);
}
