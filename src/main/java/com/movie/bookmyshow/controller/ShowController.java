package com.movie.bookmyshow.controller;

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

import com.movie.bookmyshow.entity.CinemaShow;
import com.movie.bookmyshow.service.ShowService;
import com.movie.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("show")
public class ShowController {
	
	@Autowired
	ShowService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<CinemaShow>> saveShow(@RequestBody CinemaShow show, BindingResult result) {
		return service.saveShow(show);
	}
	
	
	@GetMapping
	public ResponseEntity<ResponseStructure<CinemaShow>> findShow(@RequestParam int showId) {
		return service.findShow(showId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<CinemaShow>> deleteShow(@RequestParam int showId) {
		return service.deleteShow(showId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<CinemaShow>> updateShow(@RequestBody CinemaShow show,@RequestParam int showId) {
		return service.updateShow(show, showId);
	}

}
