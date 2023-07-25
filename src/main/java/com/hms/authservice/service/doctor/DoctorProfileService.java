package com.hms.authservice.service.doctor;

import com.hms.authservice.dto.doctor.RegisterDoctorProfileDto;
import org.springframework.http.ResponseEntity;

public interface DoctorProfileService {

    ResponseEntity<?> save(RegisterDoctorProfileDto register);
}
