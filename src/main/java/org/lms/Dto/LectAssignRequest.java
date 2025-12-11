package org.lms.Dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public class LectAssignRequest {
    @NotNull(message = "Module ID cannot be null")
    public UUID moduleId;
    
    @NotNull(message = "Lecturer ID cannot be null")
    public UUID lecturerId;
}
