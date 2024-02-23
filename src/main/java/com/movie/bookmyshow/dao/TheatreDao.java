package com.movie.bookmyshow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.bookmyshow.entity.Theatre;
import com.movie.bookmyshow.repo.TheatreRepo;

@Repository
public class TheatreDao {
	
	@Autowired
	TheatreRepo repo;
	
	public Theatre saveTheatre(Theatre theatre) {
		return repo.save(theatre);
	}
	
	public Theatre findTheatre(int theatreId) {
		Optional<Theatre> opAdmin = repo.findById(theatreId);
		if(opAdmin.isPresent()) {
			return opAdmin.get();
		}
		return null;
	}
	
	public Theatre deleteTheatre(int theatreId) {
		Theatre theatre = findTheatre(theatreId);
		repo.delete(theatre);
		return theatre;
	}
	
	public Theatre updateTheatre(Theatre theatre, int theatreId) {
		Theatre exisTheatre = findTheatre(theatreId);
		if(exisTheatre != null) {
			theatre.setTheatreId(theatreId);
			return repo.save(theatre);
		}
		return null;
	}
	
	public List<Theatre> findAllTheatre(){
		return repo.findAll();
	}
	
	public List<Theatre> findTheatreByLoc(String location) {
		return repo.findBytheatreLocation(location);
	}

}
