package com.movie.bookmyshow.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Component
public class Show {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int showId;
	private String showName;
	
	@OneToOne
	private Bookings booking;

}
