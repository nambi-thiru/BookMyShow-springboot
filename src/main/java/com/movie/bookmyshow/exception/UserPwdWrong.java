package com.movie.bookmyshow.exception;

import lombok.Getter;

@Getter
public class UserPwdWrong extends RuntimeException{
	String message;

	public UserPwdWrong(String message) {
		this.message = message;
	}
	

}
