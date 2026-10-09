package com.project.fitness.dto;

import com.project.fitness.model.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest 
{
	@NotBlank(message = "Email is Required")
	@Email(message = "Invalid Email")
	private String email ;
	
	@NotBlank(message = "Password is required ")
	@Size(min = 8, message = "Password must be at least 8 characters")
	private String password;
	
	private String firstName;
	private String lastName;
	private UserRole role;
	
	

}
