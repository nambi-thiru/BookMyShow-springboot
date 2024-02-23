package com.movie.bookmyshow.exception;

import lombok.Getter;

@Getter
public class ScreenNotFound extends RuntimeException{
	private String message;

	public ScreenNotFound(String message) {
		
		this.message = message;
	}
	

}
