package com.hms.authservice.service.user;

import com.hms.authservice.dto.LoginDto;
import com.hms.authservice.dto.RegisterDto;
import com.hms.authservice.models.Role;
import com.hms.authservice.models.User;
import org.springframework.http.ResponseEntity;


public interface IUserService {
    //ResponseEntity<?> register (RegisterDto registerDto);
    //  ResponseEntity<BearerToken> authenticate(LoginDto loginDto);

    ResponseEntity<?> authenticate(LoginDto loginDto);
    ResponseEntity<?> register (RegisterDto registerDto);
    Role saveRole(Role role);

    User saverUser (User user) ;
}
