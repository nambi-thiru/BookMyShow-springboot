package com.movie.bookmyshow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.bookmyshow.entity.Screen;
import com.movie.bookmyshow.repo.ScreenRepo;

@Repository
public class ScreenDao {
	
	@Autowired
	ScreenRepo repo;
	
	public Screen saveScreen(Screen screen) {
		return repo.save(screen);
	}
	
	public Screen findScreen(int screenId) {
		Optional<Screen> opScreen = repo.findById(screenId);
		if(opScreen.isPresent()) {
			return opScreen.get();
		}
		return null;
	}
	
	public Screen deleteScreen(int screenId) {
		Screen screen = findScreen(screenId);
		repo.delete(screen);
		return screen;
	}
	
	public Screen updateScreen(Screen screen, int screenId) {
		Screen exisScreen = findScreen(screenId);
		if(exisScreen != null) {
			screen.setScreenId(screenId);
			return repo.save(screen);
		}
		return null;
	}
	
	public List<Screen> findAllScreen(){
		return repo.findAll();
	}

}
