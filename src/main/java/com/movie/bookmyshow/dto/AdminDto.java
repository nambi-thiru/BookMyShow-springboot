package com.movie.bookmyshow.dto;

import java.util.List;

import com.movie.bookmyshow.entity.Theatre;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto {
	
	private int adminId;
	private String adminName;
	
	private List<Theatre> theatre;

}
