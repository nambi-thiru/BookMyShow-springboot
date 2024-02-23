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

import com.movie.bookmyshow.entity.Screen;
import com.movie.bookmyshow.service.ScreenService;
import com.movie.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("screen")
public class ScreenController {
	
	@Autowired
	ScreenService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Screen>> saveScreen(@RequestBody Screen screen, BindingResult result) {
		return service.saveScreen(screen);
	}
	
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Screen>> findScreen(@RequestParam int screenId) {
		return service.findScreen(screenId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Screen>> deleteScreen(@RequestParam int screenId) {
		return service.deleteScreen(screenId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Screen>> updateScreen(@RequestBody Screen screen,@RequestParam int screenId) {
		return service.updateScreen(screen, screenId);
	}

}
