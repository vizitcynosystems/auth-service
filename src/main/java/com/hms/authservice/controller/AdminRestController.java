package com.hms.authservice.controller;

import com.hms.authservice.service.patient.PatientProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminRestController {

    private final PatientProfileService patientProfileService;

    @GetMapping("/hello")
    public String sayHello ()
    { return "Hello" ;}



}
