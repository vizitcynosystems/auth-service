package com.hms.authservice;

import com.hms.authservice.models.Role;
import com.hms.authservice.models.RoleName;
import com.hms.authservice.models.User;
import com.hms.authservice.repository.IRoleRepository;
import com.hms.authservice.repository.IUserRepository;
import com.hms.authservice.service.user.IUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

//	@Bean
//	CommandLineRunner run (IUserService iUserService , IRoleRepository iRoleRepository , IUserRepository iUserRepository , PasswordEncoder passwordEncoder)
//	{return  args ->
//	{   iUserService.saveRole(new Role(RoleName.USER));
//		iUserService.saveRole(new Role(RoleName.ADMIN));
//		iUserService.saveRole(new Role(RoleName.SUPERADMIN));
//		iUserService.saverUser(new User("admin@gmail.com", passwordEncoder.encode("adminPassword"), new ArrayList<>()));
//		iUserService.saverUser(new User("superadminadmin@gmail.com", passwordEncoder.encode("superadminPassword"), new ArrayList<>()));
//
//		Role role = iRoleRepository.findByRoleName(RoleName.ADMIN);
//		User user = iUserRepository.findByEmail("admin@gmail.com").orElse(null);
//		user.getRoles().add(role);
//		iUserService.saverUser(user);
//
//		User userr = iUserRepository.findByEmail("superadminadmin@gmail.com").orElse(null);
//		Role rolee = iRoleRepository.findByRoleName(RoleName.SUPERADMIN);
//		userr.getRoles().add(rolee);
//		iUserService.saverUser(userr);
//
//	};
//	}


}
