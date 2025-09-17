package com.pm.patientservice.dtos;

import lombok.Builder;
import lombok.Getter;

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
