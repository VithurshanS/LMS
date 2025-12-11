package org.lms.Dto;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ModuleCreateRequest {
    @NotBlank(message = "Module code cannot be blank")
    @Size(min = 2, max = 20, message = "Module code must be between 2 and 20 characters")
    public String code;
    
    @NotBlank(message = "Module name cannot be blank")
    @Size(min = 2, max = 100, message = "Module name must be between 2 and 100 characters")
    public String name;
    
    @Min(value = 1, message = "Enrollment limit must be at least 1")
    public int limit;
    
    @NotNull(message = "Department ID cannot be null")
    public UUID departmentId;
    
    @NotNull(message = "Admin ID cannot be null")
    public UUID adminId;
}
