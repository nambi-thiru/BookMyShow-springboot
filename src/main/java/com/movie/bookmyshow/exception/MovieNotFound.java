package com.movie.bookmyshow.exception;

import lombok.Getter;

@Getter
public class MovieNotFound extends RuntimeException{
	
	private String message;

	public MovieNotFound(String message) {
		this.message = message;
	}
	

}
