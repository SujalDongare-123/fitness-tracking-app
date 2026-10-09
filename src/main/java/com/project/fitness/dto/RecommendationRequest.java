package com.project.fitness.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationRequest
{
	@NotBlank(message = "User id is required")
	private String userId;

	@NotBlank(message = "Activity id is required")
	private String activityId;

	private List<String> improvement;
	private  List<String> suggestion;
	private List<String> safety;

}
