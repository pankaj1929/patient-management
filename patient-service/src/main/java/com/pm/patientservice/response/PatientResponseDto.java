package com.pm.patientservice.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
public class PatientResponseDto {

    private String id;

    private String name;

    private String email;

    private String address;

    private String dateOfBirth;

//    private String registerDate;
}
