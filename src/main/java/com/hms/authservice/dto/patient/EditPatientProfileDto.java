package com.hms.authservice.dto.patient;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EditPatientProfileDto {

    @NotBlank
    private String uhid;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    @Email(message = "Invalid Email format")
    private String email;
    @NotBlank
    @Size(max = 10,message = "Ph no can be up to 10 digits")
    private String phno;
    @NotBlank
    private String fathername;
    @NotBlank
    private String country;
    @NotBlank
    private String address;
    @NotBlank
    private String gender;

}
