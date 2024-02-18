package com.movie.bookmyshow.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Component
public class Screen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int screenId;
	private String screenName;
	private int totalSeats;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<CinemaShow> shows; 

}
