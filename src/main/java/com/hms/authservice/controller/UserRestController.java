package com.hms.authservice.controller;

import com.hms.authservice.dto.LoginDto;
import com.hms.authservice.dto.RegisterDto;
import com.hms.authservice.service.user.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserRestController {

    private final IUserService iUserService ;

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterDto registerDto)
    { return  iUserService.register(registerDto);}

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginDto loginDto)
    { return  iUserService.authenticate(loginDto);
    }

    @GetMapping("/get")
    public String get() {
        return "Hello String";
    }
}
