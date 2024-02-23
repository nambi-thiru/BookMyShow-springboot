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

import com.movie.bookmyshow.entity.Theatre;
import com.movie.bookmyshow.service.TheatreService;
import com.movie.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("theatre")
public class TheatreContoller {
	
	@Autowired
	TheatreService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(@RequestBody Theatre theatre, BindingResult result) {
		return service.saveTheatre(theatre);
	}
	
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Theatre>> findTheatre(@RequestParam int theatreId) {
		return service.findTheatre(theatreId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatre(@RequestParam int theatreId) {
		return service.deleteTheatre(theatreId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(@RequestBody Theatre theatre,@RequestParam int theatreId) {
		return service.updateTheatre(theatre, theatreId);
	}
	
	@GetMapping("findtheatrebyloc")
	public ResponseEntity<ResponseStructure<List<Theatre>>> findTheatreByLoc(@RequestParam String location) {
		return service.findTheatreByLoc(location);
	}

}
