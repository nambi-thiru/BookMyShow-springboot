package com.movie.bookmyshow.exception;

import lombok.Getter;

@Getter
public class TheatreNotFound extends RuntimeException{
	private String message;

	public TheatreNotFound(String message) {
		
		this.message = message;
	}
	

}
