package com.movie.bookmyshow.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.bookmyshow.entity.Theatre;

public interface TheatreRepo extends JpaRepository<Theatre, Integer>{
	
	public List<Theatre> findBytheatreLocation(String location);

}
