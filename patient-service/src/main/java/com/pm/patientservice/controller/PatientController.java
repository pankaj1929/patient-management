package com.pm.patientservice.controller;

import com.pm.patientservice.request.PatientRequestDto;
import com.pm.patientservice.response.PatientResponseDto;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    @GetMapping("/all")
    public ResponseEntity<List<PatientResponseDto>> getListOfPatient(){
        return ResponseEntity.ok().body(patientService.getAllPatient());
    }

    @PostMapping("/createPatient")
    public ResponseEntity<PatientResponseDto> createPatient(@Valid @RequestBody PatientRequestDto patientRequestDto){
        return ResponseEntity.ok().body(patientService.createPatient(patientRequestDto));
    }
}
