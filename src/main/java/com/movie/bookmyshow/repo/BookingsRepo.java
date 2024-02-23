package com.movie.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.bookmyshow.entity.Bookings;

public interface BookingsRepo extends JpaRepository<Bookings, Integer>{

}
