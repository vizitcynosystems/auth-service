package com.hms.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginDto {

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email ;

    @NotBlank(message = "password must not be blank")
    private String password ;
}
