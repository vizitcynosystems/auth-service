package com.hms.authservice.service.patient;

import com.hms.authservice.config.ApiResponse;
import com.hms.authservice.dto.PageableResponse;
import com.hms.authservice.dto.patient.EditPatientProfileDto;
import com.hms.authservice.dto.patient.PatientProfileDto;
import com.hms.authservice.dto.patient.RegisterPatientProfileDto;
import com.hms.authservice.models.PatientProfile;
import com.hms.authservice.repository.patient.PatientProfileRepository;
import com.hms.authservice.service.patient.PatientProfileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PatientProfileServiceImp implements PatientProfileService {

    private final PatientProfileRepository patientProfileRepository;

    private final ModelMapper modelMapper;


    @Override
    public ResponseEntity<?> save(RegisterPatientProfileDto registerPatientProfileDto) {
        PatientProfile patientProfile = patientProfileRepository.findByPhno(registerPatientProfileDto.getPhno());
        if(patientProfile==null) {
            Long count = patientProfileRepository.count();
            String uhid = "SJH"+(count+1);
            registerPatientProfileDto.setUhid(uhid);
            patientProfile = modelMapper.map(registerPatientProfileDto,PatientProfile.class);
            patientProfileRepository.save(patientProfile);
            return ResponseEntity.ok().body(ApiResponse.success("Patient Profile has been created successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.successMethod("User already existed",patientProfile.getId()));

    }

    @Override
    public ResponseEntity<?> editPatientProfile(EditPatientProfileDto editPatientProfileDto) {
        PatientProfile patientProfile = patientProfileRepository.findByUhid(editPatientProfileDto.getUhid());
        if(patientProfile != null) {
            modelMapper.map(editPatientProfileDto,patientProfile);
            patientProfileRepository.save(patientProfile);
            return ResponseEntity.ok().body(ApiResponse.success("Patient Profile has been updated successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.success("Bad credentials"));
    }
    @Override
    public ResponseEntity<?> get(String uhid) {
        PatientProfile patientProfile = patientProfileRepository.findByUhid(uhid);
        return ResponseEntity.ok(patientProfile);
    }


    public PageableResponse getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<PatientProfile> patientProfiles =patientProfileRepository.findAll(pageable);
        List<PatientProfileDto> patientProfileDtos = new ArrayList<>();
        patientProfiles.forEach(patientProfile -> patientProfileDtos.add(modelMapper.map(patientProfile,PatientProfileDto.class)));
        PageableResponse response = new PageableResponse();
        response.setList(patientProfileDtos);
        response.setTotal(patientProfiles.getTotalElements());
        return response;
    }





}
