package com.movie.bookmyshow.exception;

import lombok.Getter;

@Getter
public class AdminPwdWrong extends RuntimeException{
	
	String message;

	public AdminPwdWrong(String message) {
		this.message = message;
	}
	

}
