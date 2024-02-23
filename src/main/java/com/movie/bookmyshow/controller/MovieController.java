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

import com.movie.bookmyshow.entity.Movie;
import com.movie.bookmyshow.service.MovieService;
import com.movie.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("movie")
public class MovieController {
	
	@Autowired
	MovieService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Movie>> saveMovie(@RequestBody Movie movie, BindingResult result) {
		return service.saveMovie(movie);
	}
	
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Movie>> findMovie(@RequestParam int movieId) {
		return service.findMovie(movieId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Movie>> deleteMovie(@RequestParam int movieId) {
		return service.deleteMovie(movieId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Movie>> updateMovie(@RequestBody Movie movie,@RequestParam int movieId) {
		return service.updateMovie(movie, movieId);
	}

}
