package com.movie.bookmyshow.dto;

import java.util.List;

import com.movie.bookmyshow.entity.Bookings;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	private int userId;
	private String userName;
	private long userCntct;
	
	private List<Bookings> bookings;

}
