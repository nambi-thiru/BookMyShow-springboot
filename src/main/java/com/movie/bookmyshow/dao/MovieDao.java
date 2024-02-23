package com.movie.bookmyshow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.bookmyshow.entity.Movie;
import com.movie.bookmyshow.repo.MovieRepo;

@Repository
public class MovieDao {
	
	@Autowired
	MovieRepo repo;
	
	public Movie saveMovie(Movie movie) {
		return repo.save(movie);
	}
	
	public Movie findMovie(int movieId) {
		Optional<Movie> opMovie = repo.findById(movieId);
		if(opMovie.isPresent()) {
			return opMovie.get();
		}
		return null;
	}
	
	public Movie deleteMovie(int movieId) {
		Movie movie = findMovie(movieId);
		repo.delete(movie);
		return movie;
	}
	
	public Movie updateMovie(Movie movie, int movieId) {
		Movie exisMovie = findMovie(movieId);
		if(exisMovie != null) {
			movie.setMovieId(movieId);
			return repo.save(movie);
		}
		return null;
	}
	
	public List<Movie> findAllMovie(){
		return repo.findAll();
	}
	
	public Movie findByMovieName(String movieName) {
		return repo.findBymovieName(movieName);
	}


}
