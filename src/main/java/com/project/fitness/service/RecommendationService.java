package com.project.fitness.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.fitness.dto.RecommendationRequest;
import com.project.fitness.model.Activity;
import com.project.fitness.model.Recommendation;
import com.project.fitness.model.User;
import com.project.fitness.repository.ActivityRepository;
import com.project.fitness.repository.RecommendationRepository;
import com.project.fitness.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendationService {
	
	private final RecommendationRepository recommnedationRepository;
	private final UserRepository userRepository;
	private final ActivityRepository activityRepository;
	

	public Recommendation generateRecommendation(RecommendationRequest request) 
	{
	
		User user = userRepository.findById(request.getUserId())
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: "+request.getUserId()));
		Activity activity = activityRepository.findById(request.getActivityId())
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found: "+request.getActivityId()));
		
		Recommendation recommendation = Recommendation.builder()
		.user(user)
		.activity(activity)
		.improvement(request.getImprovement())
		.suggestion(request.getSuggestion())
		.safety(request.getSafety())
		.build();
		
		Recommendation recommendationSaved = recommnedationRepository.save(recommendation);
		
		
	
		return recommendationSaved;
	}


	public List<Recommendation> getUserRecommendation(String userId) {
		
		return recommnedationRepository.findByUserId(userId);
	}


	public List<Recommendation> getActivityRecommendation(String activityId) {
		
		return recommnedationRepository.findByActivityId(activityId);
	}

}
