package com.hms.authservice.controller;

import com.hms.authservice.dto.PageableResponse;
import com.hms.authservice.dto.patient.EditPatientProfileDto;
import com.hms.authservice.dto.patient.RegisterPatientProfileDto;
import com.hms.authservice.service.patient.PatientProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientProfileService patientProfileService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid RegisterPatientProfileDto registerPatientProfileDto) {
        return patientProfileService.save(registerPatientProfileDto);
    }
    @PutMapping
    public ResponseEntity<?> edit(@RequestBody @Valid EditPatientProfileDto editPatientProfileDto) {
        return patientProfileService.editPatientProfile(editPatientProfileDto);
    }
    @GetMapping("/{uhid}")
    public ResponseEntity<?> get(@PathVariable String uhid) {
        return patientProfileService.get(uhid);
    }
    @GetMapping
    public PageableResponse getAll(@RequestParam (defaultValue = "0",required = false) int page,
                                   @RequestParam (defaultValue = "5",required = false) int size) {
        return patientProfileService.getAll(page,size);
    }
}
