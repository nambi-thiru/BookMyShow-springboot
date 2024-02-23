package com.movie.bookmyshow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.bookmyshow.dto.UserDto;
import com.movie.bookmyshow.entity.User;
import com.movie.bookmyshow.service.UserService;
import com.movie.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<UserDto>> saveUser(@RequestBody User user, BindingResult result) {
		return service.saveUser(user);
	}
	
	
	@GetMapping
	public ResponseEntity<ResponseStructure<UserDto>> findUser(@RequestParam int userId) {
		return service.findUser(userId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<UserDto>> deleteUser(@RequestParam int userId) {
		return service.deleteUser(userId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<UserDto>> updateUser(@RequestBody User user,@RequestParam int userId) {
		return service.updateUser(user, userId);
	}
	
	@GetMapping("getall")
	public ResponseEntity<ResponseStructure<List<UserDto>>> findAllUsers() {
		return service.findAllUsers();
	}
	
	@GetMapping("login")
	public ResponseEntity<ResponseStructure<UserDto>> loginUser(@RequestParam String userMail, @RequestParam String userPwd){
		return service.loginUser(userMail, userPwd);
	}
	
	@GetMapping("assignbooking")
	public ResponseEntity<ResponseStructure<UserDto>> assignBooking(@RequestParam String userMail, @RequestParam String userPwd, @RequestParam int bookingId){
		return service.assignBooking(userMail, userPwd, bookingId);
	}

}
