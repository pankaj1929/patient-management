package com.pm.patientservice.service;

import com.pm.patientservice.request.PatientRequestDto;
import com.pm.patientservice.response.PatientResponseDto;

import java.util.List;

public interface PatientService {
    List<PatientResponseDto> getAllPatient();
    PatientResponseDto createPatient(PatientRequestDto patientRequestDto);
}
