package com.movie.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.bookmyshow.entity.Movie;

public interface MovieRepo extends JpaRepository<Movie, Integer>{
	
	public Movie findBymovieName(String movieName);

}
