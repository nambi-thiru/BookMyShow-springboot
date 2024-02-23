package com.movie.bookmyshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.movie.bookmyshow.dao.MovieDao;
import com.movie.bookmyshow.dao.ShowDao;
import com.movie.bookmyshow.entity.CinemaShow;
import com.movie.bookmyshow.entity.Movie;
import com.movie.bookmyshow.exception.MovieNotFound;
import com.movie.bookmyshow.exception.ShowNotFound;
import com.movie.bookmyshow.util.ResponseStructure;

@Service
public class ShowService {
	
	@Autowired
	ShowDao dao;
	
	@Autowired
	MovieDao mdao;
	
	public ResponseEntity<ResponseStructure<CinemaShow>> saveShow(CinemaShow show) {
		ResponseStructure<CinemaShow> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("show saved");
		structure.setData(dao.saveShow(show));
		return new ResponseEntity<ResponseStructure<CinemaShow>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<CinemaShow>> findShow(int showId) {
		CinemaShow show = dao.findShow(showId);
		if(show != null) {
			ResponseStructure<CinemaShow> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("show found");
			structure.setData(show);
			return new ResponseEntity<ResponseStructure<CinemaShow>>(structure,HttpStatus.FOUND);
		}
		throw new ShowNotFound("Show not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<CinemaShow>> deleteShow(int showId) {
		CinemaShow show = dao.findShow(showId);
		if(show != null) {
			ResponseStructure<CinemaShow> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("show deleted");
			structure.setData(dao.deleteShow(showId));
			return new ResponseEntity<ResponseStructure<CinemaShow>>(structure,HttpStatus.OK);
		}
		throw new ShowNotFound("Show not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<CinemaShow>> updateShow(CinemaShow show, int showId) {
		CinemaShow lap = dao.findShow(showId);
		if(lap != null) {
			ResponseStructure<CinemaShow> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("show updated");
			structure.setData(dao.updateShow(show, showId));
			return new ResponseEntity<ResponseStructure<CinemaShow>>(structure,HttpStatus.OK);
		}
		throw new ShowNotFound("Show not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<CinemaShow>> addMovieToShow(int movieId, int showId) {
		CinemaShow show = dao.findShow(showId);
		Movie mv = mdao.findMovie(movieId);
		if(show != null) {
			if(mv != null) {
				show.setMovie(mv);
				ResponseStructure<CinemaShow> structure = new ResponseStructure<>();
				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage("show updated");
				structure.setData(dao.updateShow(show, show.getShowId()));
				return new ResponseEntity<ResponseStructure<CinemaShow>>(structure,HttpStatus.OK);
			}
			throw new MovieNotFound("Movie not found for the given id");
		}
		throw new ShowNotFound("Show not found for the given id");
	}

}
