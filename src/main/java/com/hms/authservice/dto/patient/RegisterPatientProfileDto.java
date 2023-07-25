package com.hms.authservice.dto.patient;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class RegisterPatientProfileDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "First name is required")
    private String firstname;
    @NotBlank(message = "Lastname is required ")
    private String lastname;
    @NotBlank(message = "Father name or Spouse name required")
    private String fathername;
    @NotBlank(message = "Phone no is required")
    private String phno;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Gender is required")
    private String gender;
    @NotBlank(message = "Country is required")
    private String country;

    private String uhid;
}
