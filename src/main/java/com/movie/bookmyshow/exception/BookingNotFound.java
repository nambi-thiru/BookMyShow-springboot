package com.movie.bookmyshow.exception;

import lombok.Getter;

@Getter
public class BookingNotFound extends RuntimeException{
	
	private String message;

	public BookingNotFound(String message) {
		
		this.message = message;
	}
	

}
