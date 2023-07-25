package com.hms.authservice.dto.doctor;

import lombok.Data;

import java.time.LocalDate;
@Data
public class RegisterDoctorProfileDto {

    private String firstname;

    private String lastname;

    private String speciality;

    private String department;

    private String phno;

    private String email;

    private LocalDate joiningDate;
}
