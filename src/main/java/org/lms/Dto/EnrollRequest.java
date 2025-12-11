package org.lms.Dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public class EnrollRequest {
    @NotNull(message = "Student ID cannot be null")
    public UUID studentId;
    
    @NotNull(message = "Module ID cannot be null")
    public UUID moduleId;
}
