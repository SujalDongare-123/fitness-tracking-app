package com.project.fitness.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity
{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id" , nullable = false  , foreignKey =@ForeignKey(name="fk_activity_user") )
	@JsonIgnore
	private User user;
	
	@Enumerated(EnumType.STRING)  //•  This forces Hibernate to save the exact text value of the enum as a string in the database column (e.g., it will literally save the text "RUNNING" or "CYCLING").
	private ActivityType type;
	
	@JdbcTypeCode(SqlTypes.JSON) //•  This tells Hibernate 7 how to handle the data transfer. It instructs Hibernate to automatically serialize your Java Map into a JSON string when saving it to the database, and automatically deserialize the database JSON string back into a Java Map when retrieving it.
	@Column(columnDefinition = "json") //This tells MySQL to create a specific JSON data type column in the table instead of a standard VARCHAR or TEXT.
	private Map<String, Object> additonalMetrices;
	
	private Integer duration;
	
	private Integer caloriesBurned;
	
	private LocalDateTime startTime;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updateAt;
	
	
	
	@OneToMany(mappedBy = "activity" , cascade =CascadeType.ALL , orphanRemoval = true)
	@JsonIgnore
	private List<Recommendation> recommendation = new ArrayList<>();	

}
