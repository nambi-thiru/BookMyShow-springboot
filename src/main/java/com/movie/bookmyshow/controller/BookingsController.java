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

import com.movie.bookmyshow.entity.Bookings;
import com.movie.bookmyshow.service.BookingsService;
import com.movie.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("bookings")
public class BookingsController {
	
	@Autowired
	BookingsService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Bookings>> saveBookings(@RequestBody Bookings bookings, BindingResult result) {
		return service.saveBookings(bookings);
	}
	
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Bookings>> findBookings(@RequestParam int bookingsId) {
		return service.findBookings(bookingsId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Bookings>> deleteBookings(@RequestParam int bookingsId) {
		return service.deleteBookings(bookingsId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Bookings>> updateBookings(@RequestBody Bookings bookings,@RequestParam int bookingsId) {
		return service.updateBookings(bookings, bookingsId);
	}
	
	@GetMapping("findbyuser")
	public ResponseEntity<ResponseStructure<List<Bookings>>> findBookingsByUser(@RequestParam String userMail,@RequestParam String userPwd){
		return service.findBookingsByUser(userMail, userPwd);
	}

}
