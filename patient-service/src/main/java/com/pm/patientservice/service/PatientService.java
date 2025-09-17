package com.pm.patientservice.service;

import com.pm.patientservice.dtos.PatientRequestDto;
import com.pm.patientservice.dtos.PatientResponseDto;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    List<PatientResponseDto> getAllPatient();
    PatientResponseDto createPatient(PatientRequestDto patientRequestDto);
    PatientResponseDto updatePatient(UUID uuid,PatientRequestDto patientRequestDto);
    void deletePatient(UUID id);
}
