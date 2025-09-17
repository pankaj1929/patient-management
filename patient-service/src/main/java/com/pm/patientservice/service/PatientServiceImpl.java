package com.pm.patientservice.service;

import com.pm.patientservice.exception.EmailAllReadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import com.pm.patientservice.dtos.PatientRequestDto;
import com.pm.patientservice.dtos.PatientResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;

    @Override
    public List<PatientResponseDto> getAllPatient() {
        var patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDto).toList();
    }

    @Override
    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto) {
        if (patientRepository.existsByEmail(patientRequestDto.getEmail())){
            throw new EmailAllReadyExistsException("A Patient with this email alredy exists " + patientRequestDto.getEmail());
        }
        Patient patient = patientRepository.save(PatientMapper.toEntity(patientRequestDto));
        return PatientMapper.toDto(patient);
    }

    @Override
    public PatientResponseDto updatePatient(UUID uuid, PatientRequestDto patientRequestDto) {
        var patient = patientRepository.findById(uuid).orElseThrow(() -> new PatientNotFoundException("Patient not found with id " + uuid) );
        if (patientRepository.existsByEmailAndIdNot(patientRequestDto.getEmail(),uuid)){
            throw new EmailAllReadyExistsException("A Patient with this email alredy exists " + patientRequestDto.getEmail());
        }
        patient.setName(patientRequestDto.getName());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));

        var patientUpdate = patientRepository.save(patient);

        return PatientMapper.toDto(patientUpdate);
    }

    @Override
    public void deletePatient(UUID id) {
        var patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with id " + id) );
        patientRepository.deleteById(patient.getId());
    }
}
