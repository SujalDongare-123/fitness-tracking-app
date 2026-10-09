package com.project.fitness.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fitness.dto.ActivityReponse;
import com.project.fitness.dto.ActivityRequest;
import com.project.fitness.service.ActivityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController 
{
	private final ActivityService activityService;
	
	@PostMapping
	public ResponseEntity<ActivityReponse> trackActivity(@Valid @RequestBody ActivityRequest request)
	{
		
		
		return ResponseEntity.ok(activityService.TrackActivity(request));
	}
	
	@GetMapping
	public ResponseEntity<List<ActivityReponse>> getUserActivities(@RequestHeader(value ="X-User-ID") String userId)
	{
		return ResponseEntity.ok(activityService.getUserActivities(userId));
	}

}
