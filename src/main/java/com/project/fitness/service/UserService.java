package com.project.fitness.service;



import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.fitness.dto.LoginRequest;
import com.project.fitness.dto.RegisterRequest;
import com.project.fitness.dto.UserResponse;
import com.project.fitness.model.User;
import com.project.fitness.model.UserRole;
import com.project.fitness.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserResponse register(RegisterRequest request) 
	{
		if(userRepository.existsByEmail(request.getEmail()))
		{
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already registered");
		}
		
	User user=User.builder()
				  .email(request.getEmail())
				  .firstName(request.getFirstName())
				  .lastName(request.getLastName())
				  .password(passwordEncoder.encode(request.getPassword()))
				  .role(UserRole.USER)
				  .build();
	
	
	
	
		User savedUser = userRepository.save(user);
				
		return mapToRespone(savedUser);
	}

	
	public UserResponse mapToRespone(User savedUser)
	{
		UserResponse response=new UserResponse();
		
				response.setId(savedUser.getId());
				response.setEmail(savedUser.getEmail());
				response.setFirstName(savedUser.getFirstName());
				response.setLastName(savedUser.getLastName());
				response.setCreatedAt(savedUser.getCreatedAt());
				response.setUpdatedAt(savedUser.getUpdatedAt());
				
		
		return response;
	}


	public User authenticate(LoginRequest loginRequest)
	{
		
		User user = userRepository.findByEmail(loginRequest.getEmail());
		if(user== null)
		{
			throw new BadCredentialsException("Invalid Credentials");
		}
		
		if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
			{
			throw new BadCredentialsException("Invalid Credentials");
			}
			
		return user;
	}
	
	
	
	
	
	
	

}
