package com.movie.bookmyshow.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Component
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@NotNull(message = "user name cannot be null")
	@NotEmpty(message = "user name cannot be empty")
	private String userName;
	@Positive
	@NotNull(message = "user contact cannot be null")
	@NotEmpty(message = "user contact cannot be empty")
	private long userCntct;
	@Email
	@NotNull(message = "user mail cannot be null")
	@NotEmpty(message = "user Mail cannot be empty")
	private String userMail;
	@NotNull(message = "user Pwd cannot be null")
	@NotEmpty(message = "user Pwd cannot be empty")
	private String userPwd;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Bookings> bookings;

}
