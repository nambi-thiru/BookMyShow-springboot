package com.movie.bookmyshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.movie.bookmyshow.dao.MovieDao;
import com.movie.bookmyshow.entity.Movie;
import com.movie.bookmyshow.exception.MovieNotFound;
import com.movie.bookmyshow.util.ResponseStructure;

@Service
public class MovieService {
	
	@Autowired
	MovieDao dao;
	
	public ResponseEntity<ResponseStructure<Movie>> saveMovie(Movie movie) {
		ResponseStructure<Movie> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("movie saved");
		structure.setData(dao.saveMovie(movie));
		return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Movie>> findMovie(int movieId) {
		Movie movie = dao.findMovie(movieId);
		if(movie != null) {
			ResponseStructure<Movie> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("movie found");
			structure.setData(movie);
			return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.FOUND);
		}
		throw new MovieNotFound("Movie not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Movie>> deleteMovie(int movieId) {
		Movie movie = dao.findMovie(movieId);
		if(movie != null) {
			ResponseStructure<Movie> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("movie deleted");
			structure.setData(dao.deleteMovie(movieId));
			return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.OK);
		}
		throw new MovieNotFound("Movie not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Movie>> updateMovie(Movie movie, int movieId) {
		Movie lap = dao.findMovie(movieId);
		if(lap != null) {
			ResponseStructure<Movie> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("movie updated");
			structure.setData(dao.updateMovie(movie, movieId));
			return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.OK);
		}
		throw new MovieNotFound("Movie not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Movie>> findByMovieName(String movieName) {
		Movie movie = dao.findByMovieName(movieName);
		if(movie != null) {
			ResponseStructure<Movie> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("movie found");
			structure.setData(movie);
			return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.FOUND);
		}
		throw new MovieNotFound("Movie not found for the given Name");
	}

}
