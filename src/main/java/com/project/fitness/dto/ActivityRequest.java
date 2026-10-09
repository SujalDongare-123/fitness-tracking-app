package com.project.fitness.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.project.fitness.model.ActivityType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequest 
{
	@NotBlank(message = "User id is required")
	private String userId;

	@NotNull(message = "Activity type is required")
	private ActivityType type;

	private Map<String, Object> additonalMetrices;

	@NotNull(message = "Duration is required")
	@Positive(message = "Duration must be greater than zero")
	private Integer duration;

	@NotNull(message = "Calories burned is required")
	@PositiveOrZero(message = "Calories burned cannot be negative")
	private Integer caloriesBurned;

	@NotNull(message = "Start time is required")
	private LocalDateTime startTime;

}
