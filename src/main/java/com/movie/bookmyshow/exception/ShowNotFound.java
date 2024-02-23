package com.movie.bookmyshow.exception;

import lombok.Getter;

@Getter
public class ShowNotFound extends RuntimeException{
	private String message;

	public ShowNotFound(String message) {
		
		this.message = message;
	}

}
