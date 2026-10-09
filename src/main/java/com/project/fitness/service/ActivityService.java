package com.project.fitness.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.fitness.dto.ActivityReponse;
import com.project.fitness.dto.ActivityRequest;
import com.project.fitness.model.Activity;
import com.project.fitness.model.User;
import com.project.fitness.repository.ActivityRepository;
import com.project.fitness.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityService 
{
	
	private final ActivityRepository activityRepository;
	private final UserRepository userRepository;

	public ActivityReponse  TrackActivity(ActivityRequest request) 
	{
		 User user=userRepository.findById(request.getUserId())
				 .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: "+request.getUserId()));
		
		Activity activity=Activity.builder()
								  .type(request.getType())
								  .user(user)
								  .duration(request.getDuration())
								  .caloriesBurned(request.getCaloriesBurned())
								  .startTime(request.getStartTime())
								  .additonalMetrices(request.getAdditonalMetrices())
								  .build();
		
		 Activity savedActivity = activityRepository.save(activity);
		
		return mapToResponse(savedActivity) ;
	}

	
	private ActivityReponse mapToResponse(Activity activity)
	{
		ActivityReponse response= new ActivityReponse();
		
		response.setId(activity.getId());
		response.setUserId(activity.getUser().getId());
		response.setType(activity.getType());
		response.setDuration(activity.getDuration());
		response.setCaloriesBurned(activity.getCaloriesBurned());
		response.setStartTime(activity.getStartTime());
		response.setAdditonalMetrices(activity.getAdditonalMetrices());
		response.setCreatedAt(activity.getCreatedAt());
		response.setUpdateAt(activity.getUpdateAt());
		
		
		
		return response;
	}


	public List<ActivityReponse> getUserActivities(String userId) 
	{
	
		List<Activity> activityList = activityRepository.findByUserId(userId);

		return activityList.stream().map(this::mapToResponse).collect(Collectors.toList());
	}
	
	

}
