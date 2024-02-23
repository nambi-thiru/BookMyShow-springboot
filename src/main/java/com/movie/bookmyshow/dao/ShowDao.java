package com.movie.bookmyshow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.bookmyshow.entity.CinemaShow;
import com.movie.bookmyshow.repo.ShowRepo;

@Repository
public class ShowDao {
	
	@Autowired
	ShowRepo repo;
	
	public CinemaShow saveShow(CinemaShow show) {
		return repo.save(show);
	}
	
	public CinemaShow findShow(int showId) {
		Optional<CinemaShow> opShow = repo.findById(showId);
		if(opShow.isPresent()) {
			return opShow.get();
		}
		return null;
	}
	
	public CinemaShow deleteShow(int showId) {
		CinemaShow show = findShow(showId);
		repo.delete(show);
		return show;
	}
	
	public CinemaShow updateShow(CinemaShow show, int showId) {
		CinemaShow exisShow = findShow(showId);
		if(exisShow != null) {
			show.setShowId(showId);
			return repo.save(show);
		}
		return null;
	}
	
	public List<CinemaShow> findAllShow(){
		return repo.findAll();
	}


}
