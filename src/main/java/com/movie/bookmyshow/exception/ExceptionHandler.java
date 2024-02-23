package com.movie.bookmyshow.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.movie.bookmyshow.util.ResponseStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<com.movie.bookmyshow.util.ResponseStructure<String>> adminNotFoundException(AdminNotFound ex){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(ex.getMessage());
		structure.setMessage("Admin doesn't exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<com.movie.bookmyshow.util.ResponseStructure<String>> userNotFoundException(UserNotFound ex){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(ex.getMessage());
		structure.setMessage("User doesn't exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<com.movie.bookmyshow.util.ResponseStructure<String>> theatreNotFoundException(TheatreNotFound ex){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(ex.getMessage());
		structure.setMessage("Theatre doesn't exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<com.movie.bookmyshow.util.ResponseStructure<String>> screenNotFoundException(ScreenNotFound ex){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(ex.getMessage());
		structure.setMessage("Screen doesn't exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<com.movie.bookmyshow.util.ResponseStructure<String>> showNotFoundException(ShowNotFound ex){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(ex.getMessage());
		structure.setMessage("Show doesn't exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<com.movie.bookmyshow.util.ResponseStructure<String>> bookingNotFoundException(BookingNotFound ex){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(ex.getMessage());
		structure.setMessage("Booking doesn't exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<com.movie.bookmyshow.util.ResponseStructure<String>> movieNotFoundException(MovieNotFound ex){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(ex.getMessage());
		structure.setMessage("Movie doesn't exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<com.movie.bookmyshow.util.ResponseStructure<Object>> constraintViolationException(ConstraintViolationException ex){
		ResponseStructure<Object> structure = new ResponseStructure<>();
		Map<String, String> hashMap = new HashMap<String, String>();
		for(ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			String field = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			hashMap.put(field, message);
		}
		structure.setData(hashMap);
		structure.setMessage("add valid details");
		structure.setStatus(HttpStatus.FORBIDDEN.value());
		return new ResponseEntity<ResponseStructure<Object>>(structure,HttpStatus.FORBIDDEN);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<com.movie.bookmyshow.util.ResponseStructure<String>> userPwdWrongException(UserPwdWrong ex){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(ex.getMessage());
		structure.setMessage("User Password Wrong");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<com.movie.bookmyshow.util.ResponseStructure<String>> adminPwdWrongException(AdminPwdWrong ex){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(ex.getMessage());
		structure.setMessage("Admin Password Wrong");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}

}
