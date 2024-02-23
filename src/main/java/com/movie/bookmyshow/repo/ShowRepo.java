package com.movie.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.bookmyshow.entity.CinemaShow;

public interface ShowRepo extends JpaRepository<CinemaShow, Integer>{

}
