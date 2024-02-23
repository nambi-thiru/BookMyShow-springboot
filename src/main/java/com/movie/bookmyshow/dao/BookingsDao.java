package com.movie.bookmyshow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.bookmyshow.entity.Bookings;
import com.movie.bookmyshow.repo.BookingsRepo;

@Repository
public class BookingsDao {
	
	@Autowired
	BookingsRepo repo;
	
	public Bookings saveBookings(Bookings bookings) {
		return repo.save(bookings);
	}
	
	public Bookings findBookings(int bookingsId) {
		Optional<Bookings> opBookings = repo.findById(bookingsId);
		if(opBookings.isPresent()) {
			return opBookings.get();
		}
		return null;
	}
	
	public Bookings deleteBookings(int bookingsId) {
		Bookings bookings = findBookings(bookingsId);
		repo.delete(bookings);
		return bookings;
	}
	
	public Bookings updateBookings(Bookings bookings, int bookingsId) {
		Bookings exisBookings = findBookings(bookingsId);
		if(exisBookings != null) {
			bookings.setBookingId(bookingsId);
			return repo.save(bookings);
		}
		return null;
	}
	
	public List<Bookings> findAllBookings(){
		return repo.findAll();
	}


}
