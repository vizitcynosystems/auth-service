package com.hms.authservice.service.doctor;

import com.hms.authservice.config.ApiResponse;
import com.hms.authservice.dto.doctor.RegisterDoctorProfileDto;
import com.hms.authservice.models.DoctorProfile;
import com.hms.authservice.repository.doctor.DoctorProfileRepository;
import com.hms.authservice.service.doctor.DoctorProfileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorProfileServiceImp implements DoctorProfileService {

    private final DoctorProfileRepository doctorProfileRepository;

    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> save(RegisterDoctorProfileDto register) {

        DoctorProfile doctorProfile = modelMapper.map(register,DoctorProfile.class);
        doctorProfileRepository.save(doctorProfile);
        return ResponseEntity.ok().body(ApiResponse.success("Profile has been created successfully"));
    }


}
