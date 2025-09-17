package com.pm.patientservice.controller;

import com.pm.patientservice.dtos.PatientRequestDto;
import com.pm.patientservice.dtos.PatientResponseDto;
import com.pm.patientservice.dtos.validators.CreatePatientValidationGroup;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/patients")
@Tag(name="Patientss",description = "Api for Managing Patients")
public class PatientController {
    private final PatientService patientService;

    @GetMapping("/all")
    @Operation(summary = "get all patients")
    public ResponseEntity<List<PatientResponseDto>> getListOfPatient(){
        return ResponseEntity.ok().body(patientService.getAllPatient());
    }

    @PostMapping("/createPatient")
    @Operation(summary = "create patients")

    public ResponseEntity<PatientResponseDto> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDto patientRequestDto){
        return ResponseEntity.ok().body(patientService.createPatient(patientRequestDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "update patients")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID id,
                                                            @Validated({Default.class}) @RequestBody PatientRequestDto patientRequestDto){
        return ResponseEntity.ok().body(patientService.updatePatient(id,patientRequestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete patients")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
