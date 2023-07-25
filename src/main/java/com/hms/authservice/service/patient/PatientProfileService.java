package com.hms.authservice.service.patient;

import com.hms.authservice.dto.PageableResponse;
import com.hms.authservice.dto.patient.EditPatientProfileDto;
import com.hms.authservice.dto.patient.RegisterPatientProfileDto;
import org.springframework.http.ResponseEntity;

public interface PatientProfileService {

    ResponseEntity<?> save(RegisterPatientProfileDto patientProfile);

    ResponseEntity<?> editPatientProfile(EditPatientProfileDto editPatientProfileDto);

    ResponseEntity<?> get(String uhid) ;

    PageableResponse getAll(int page , int size);
}
