package com.hms.authservice.controller;

import com.hms.authservice.dto.doctor.RegisterDoctorProfileDto;
import com.hms.authservice.service.doctor.DoctorProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorProfileService doctorProfileService;
    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterDoctorProfileDto profileDto) {
        return doctorProfileService.save(profileDto);
    }
}
