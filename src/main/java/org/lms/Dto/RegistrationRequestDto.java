package org.lms.Dto;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegistrationRequestDto {

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    public String username;
    
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 15, message = "Password should be within 8 to 15 characters")
    public String password;
    
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be a valid email address")
    public String email;
    
    @NotBlank(message = "First name cannot be blank")
    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    public String firstName;
    
    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    public String lastName;
    
    @NotBlank(message = "Role cannot be blank")
    @Pattern(regexp = "^(student|lecturer|admin)$", message = "Role must be either 'student', 'lecturer', or 'admin'")
    public String role;
    
    public UUID departmentId;
}
