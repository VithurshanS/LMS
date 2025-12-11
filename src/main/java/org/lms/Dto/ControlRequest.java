package org.lms.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ControlRequest {
    @NotBlank(message = "ID cannot be blank")
    public String id;
    
    @NotBlank(message = "Control action cannot be blank")
    @Pattern(regexp = "^(ban|unban)$", message = "Control action must be either 'ban' or 'unban'")
    public String control;
    
    @NotBlank(message = "Role cannot be blank")
    @Pattern(regexp = "^(student|lecturer)$", message = "Role must be either 'student' or 'lecturer'")
    public String role;
}
