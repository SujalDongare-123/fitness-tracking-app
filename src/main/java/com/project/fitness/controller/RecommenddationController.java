package com.project.fitness.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fitness.dto.RecommendationRequest;
import com.project.fitness.model.Recommendation;
import com.project.fitness.service.RecommendationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommenddationController 
{
	private final RecommendationService recommendationService;
	
	@PostMapping("/generate")
	public ResponseEntity<Recommendation> generateRecommendation(@Valid @RequestBody RecommendationRequest request )
	{
		Recommendation recommendation=recommendationService.generateRecommendation(request);
		
		return ResponseEntity.ok(recommendation);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Recommendation>> getUserRecommendation(@PathVariable String userId)
	{
		List<Recommendation> recommendationList=recommendationService.getUserRecommendation(userId);
		return ResponseEntity.ok(recommendationList);
	}
	
	@GetMapping("/activity/{activityId}")
	public ResponseEntity<List<Recommendation>> getActivityRecommendation(@PathVariable String activityId)
	{
		List<Recommendation> recommendationList=recommendationService.getActivityRecommendation(activityId);
		return ResponseEntity.ok(recommendationList);
	}
	
	

}
