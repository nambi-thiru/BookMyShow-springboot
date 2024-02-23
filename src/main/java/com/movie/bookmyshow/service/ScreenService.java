package com.movie.bookmyshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.movie.bookmyshow.dao.ScreenDao;
import com.movie.bookmyshow.dao.ShowDao;
import com.movie.bookmyshow.entity.CinemaShow;
import com.movie.bookmyshow.entity.Screen;
import com.movie.bookmyshow.exception.ScreenNotFound;
import com.movie.bookmyshow.exception.ShowNotFound;
import com.movie.bookmyshow.util.ResponseStructure;

@Service
public class ScreenService {
	
	@Autowired
	ScreenDao dao;
	
	@Autowired
	ShowDao sdao;
	
	public ResponseEntity<ResponseStructure<Screen>> saveScreen(Screen screen) {
		ResponseStructure<Screen> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("screen saved");
		structure.setData(dao.saveScreen(screen));
		return new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Screen>> findScreen(int screenId) {
		Screen screen = dao.findScreen(screenId);
		if(screen != null) {
			ResponseStructure<Screen> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("screen found");
			structure.setData(screen);
			return new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.FOUND);
		}
		throw new ScreenNotFound("Screen not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Screen>> deleteScreen(int screenId) {
		Screen screen = dao.findScreen(screenId);
		if(screen != null) {
			ResponseStructure<Screen> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("screen deleted");
			structure.setData(dao.deleteScreen(screenId));
			return new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.OK);
		}
		throw new ScreenNotFound("Screen not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Screen>> updateScreen(Screen screen, int screenId) {
		Screen sc = dao.findScreen(screenId);
		if(sc != null) {
			ResponseStructure<Screen> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("screen updated");
			structure.setData(dao.updateScreen(screen, screen.getScreenId()));
			return new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.OK);
		}
		throw new ScreenNotFound("Screen not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Screen>> addShowToScreen(int showId, int screenId){
		Screen sc = dao.findScreen(screenId);
		CinemaShow show = sdao.findShow(showId);
		if(sc != null) {
			if(show != null) {
				show.setScreenId(screenId);
				sc.getShows().add(show);
				ResponseStructure<Screen> structure = new ResponseStructure<>();
				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage("screen updated");
				structure.setData(dao.updateScreen(sc, sc.getScreenId()));
				return new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.OK);
			}
			throw new ShowNotFound("show not found for the given id");
		}
		throw new ScreenNotFound("Screen not found for the given id");
	}

}
