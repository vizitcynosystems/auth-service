package com.hms.authservice.dto.patient;

import lombok.Data;

import java.util.UUID;
@Data
public class PatientProfileDto {

    private UUID id;

    private String email;

    private String firstname;

    private String lastname;

    private String fathername;

    private String phno;

    private String address;

    private String gender;

    private String country;

    private String uhid;
}
