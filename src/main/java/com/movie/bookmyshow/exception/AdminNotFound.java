package com.movie.bookmyshow.exception;

import lombok.Getter;

@Getter
public class AdminNotFound extends RuntimeException{
	private String message;

	public AdminNotFound(String message) {
		this.message = message;
	}
	

}
