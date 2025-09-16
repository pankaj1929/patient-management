package com.pm.patientservice.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PatientRequestDto {
    @NotBlank(message = "name is required")
    @Size(max = 100, message = "name cannot exceeds 100 characters")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "email should be valid")
    private String email;

    @NotBlank(message = "address is required")
    private String address;

    @NotBlank(message = "date of birth is required")
    private String dateOfBirth;

    @NotBlank(message = "registered Date is required")
    private String registeredDate;
}
