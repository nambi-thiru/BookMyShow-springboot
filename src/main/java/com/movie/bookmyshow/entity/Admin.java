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
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Component
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	@NotNull(message = "admin name cannot be null")
	@NotEmpty(message = "admin name cannot be emppty")
	private String adminName;
	@NotNull
	@NotEmpty
	@Email(message = "admin should have proper mail Id")
	private String adminMail;
	@NotNull
	@NotEmpty
//	@Pattern(regexp = "")
	private String adminPwd;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Theatre> theatre;

}
