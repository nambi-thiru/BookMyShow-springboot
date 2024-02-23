package com.movie.bookmyshow.exception;

import lombok.Getter;

@Getter
public class UserNotFound extends RuntimeException{
	private String message;

	public UserNotFound(String message) {
		this.message = message;
	}

}
