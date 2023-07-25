package com.hms.authservice.service.user;

import com.hms.authservice.dto.BearerToken;
import com.hms.authservice.dto.LoginDto;
import com.hms.authservice.dto.RegisterDto;
import com.hms.authservice.models.Role;
import com.hms.authservice.models.RoleName;
import com.hms.authservice.models.User;
import com.hms.authservice.repository.IRoleRepository;
import com.hms.authservice.repository.IUserRepository;
import com.hms.authservice.security.JwtUtilities;
import com.hms.authservice.service.user.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final AuthenticationManager authenticationManager ;
    private final IUserRepository iUserRepository ;
    private final IRoleRepository iRoleRepository ;
    private final PasswordEncoder passwordEncoder ;
    private final JwtUtilities jwtUtilities ;


    @Override
    public Role saveRole(Role role) {
        return iRoleRepository.save(role);
    }

    @Override
    public User saverUser(User user) {
        return iUserRepository.save(user);
    }

    @Override
    public ResponseEntity<?> register(RegisterDto registerDto) {
        if(iUserRepository.existsByEmail(registerDto.getEmail()))
        { return  new ResponseEntity<>("email is already taken !", HttpStatus.SEE_OTHER); }
        else
        { User user = new User();
            user.setEmail(registerDto.getEmail());
            user.setFirstName(registerDto.getFirstName());
            user.setLastName(registerDto.getLastName());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            //By Default , he/she is a simple user
            Role role = iRoleRepository.findByRoleName(RoleName.USER);
            user.setRoles(Collections.singletonList(role));
            iUserRepository.save(user);
            String token = jwtUtilities.generateToken(registerDto.getEmail(),Collections.singletonList(role.getRoleName()));
            return new ResponseEntity<>(new BearerToken(token , true),HttpStatus.OK);

        }
    }

    @Override
    public ResponseEntity<?> authenticate(LoginDto loginDto) {
        User user = iUserRepository.findByEmail(loginDto.getEmail()).orElseThrow(() ->  new BadCredentialsException("User Not Found")) ;
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User newUser = iUserRepository.findByEmail(authentication.getName()).orElseThrow(() -> new  BadCredentialsException("User not found"));
        List<String> rolesNames = new ArrayList<>();
        newUser.getRoles().forEach(r-> rolesNames.add(r.getRoleName()));
        String token = jwtUtilities.generateToken(newUser.getUsername(),rolesNames);
        BearerToken bearerToken = new BearerToken(token , true);
        return ResponseEntity.ok().body(bearerToken);
    }

}
