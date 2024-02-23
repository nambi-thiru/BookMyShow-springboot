package com.movie.bookmyshow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.movie.bookmyshow.dao.ScreenDao;
import com.movie.bookmyshow.dao.TheatreDao;
import com.movie.bookmyshow.entity.Screen;
import com.movie.bookmyshow.entity.Theatre;
import com.movie.bookmyshow.exception.ScreenNotFound;
import com.movie.bookmyshow.exception.TheatreNotFound;
import com.movie.bookmyshow.util.ResponseStructure;

@Service
public class TheatreService {
	
	@Autowired
	TheatreDao dao;
	
	@Autowired
	ScreenDao sdao;
	
	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(Theatre theatre) {
		ResponseStructure<Theatre> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("theatre saved");
		structure.setData(dao.saveTheatre(theatre));
		return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> findTheatre(int theatreId) {
		Theatre theatre = dao.findTheatre(theatreId);
		if(theatre != null) {
			ResponseStructure<Theatre> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("theatre found");
			structure.setData(theatre);
			return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.FOUND);
		}
		throw new TheatreNotFound("Theatre not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatre(int theatreId) {
		Theatre theatre = dao.findTheatre(theatreId);
		if(theatre != null) {
			ResponseStructure<Theatre> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("theatre deleted");
			structure.setData(dao.deleteTheatre(theatreId));
			return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.OK);
		}
		throw new TheatreNotFound("Theatre not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(Theatre theatre, int theatreId) {
		Theatre lap = dao.findTheatre(theatreId);
		if(lap != null) {
			ResponseStructure<Theatre> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("theatre updated");
			structure.setData(dao.updateTheatre(theatre, theatreId));
			return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.OK);
		}
		throw new TheatreNotFound("Theatre not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<List<Theatre>>> findTheatreByLoc(String location) {
		List<Theatre> theatre = dao.findTheatreByLoc(location);
		if(theatre != null) {
			ResponseStructure<List<Theatre>> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("theatres found in " + location);
			structure.setData(theatre);
			return new ResponseEntity<ResponseStructure<List<Theatre>>>(structure,HttpStatus.FOUND);
		}
		throw new TheatreNotFound("Theatre not found for the given location");
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> assignScreen(int theatreId, int screenId){
		Theatre theatre = dao.findTheatre(theatreId);
		if(theatre != null) {
			Screen screen = sdao.findScreen(screenId);
			if(screen != null) {
				theatre.getScreens().add(screen);
				ResponseStructure<Theatre> structure = new ResponseStructure<>();
				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage("theatre updated");
				structure.setData(dao.updateTheatre(theatre, theatre.getTheatreId()));
				return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.OK);
			}
			throw new ScreenNotFound("Screen not found for the given location");
		}
		throw new TheatreNotFound("Theatre not found for the given location");
	}
	
	

}
