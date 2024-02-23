package com.movie.bookmyshow.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.movie.bookmyshow.dao.BookingsDao;
import com.movie.bookmyshow.dto.UserDto;
import com.movie.bookmyshow.entity.Bookings;
import com.movie.bookmyshow.entity.User;
import com.movie.bookmyshow.exception.BookingNotFound;
import com.movie.bookmyshow.exception.UserNotFound;
import com.movie.bookmyshow.exception.UserPwdWrong;
import com.movie.bookmyshow.util.ResponseStructure;


@Service
public class BookingsService {
	
	@Autowired
	BookingsDao dao;
	
	@Autowired
	UserService uservice;
	
	public ResponseEntity<ResponseStructure<Bookings>> saveBookings(Bookings bookings) {
		ResponseStructure<Bookings> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("bookings saved");
		structure.setData(dao.saveBookings(bookings));
		return new ResponseEntity<ResponseStructure<Bookings>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Bookings>> findBookings(int bookingsId) {
		Bookings bookings = dao.findBookings(bookingsId);
		if(bookings != null) {
			ResponseStructure<Bookings> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("bookings found");
			structure.setData(bookings);
			return new ResponseEntity<ResponseStructure<Bookings>>(structure,HttpStatus.FOUND);
		}
		throw new BookingNotFound("Booking not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Bookings>> deleteBookings(int bookingsId) {
		Bookings bookings = dao.findBookings(bookingsId);
		if(bookings != null) {
			ResponseStructure<Bookings> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("bookings deleted");
			structure.setData(dao.deleteBookings(bookingsId));
			return new ResponseEntity<ResponseStructure<Bookings>>(structure,HttpStatus.OK);
		}
		throw new BookingNotFound("Booking not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Bookings>> updateBookings(Bookings bookings, int bookingsId) {
		Bookings booking = dao.findBookings(bookingsId);
		if(booking != null) {
			ResponseStructure<Bookings> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("bookings updated");
			structure.setData(dao.updateBookings(bookings, bookingsId));
			return new ResponseEntity<ResponseStructure<Bookings>>(structure,HttpStatus.OK);
		}
		throw new BookingNotFound("Booking not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<List<Bookings>>> findBookingsByUser(String userMail, String userPwd) {
		ResponseEntity<ResponseStructure<UserDto>> user = uservice.loginUser(userMail, userPwd);
		if(user.getBody().getData().getUserName() != null) {
			List<Bookings> bookings = user.getBody().getData().getBookings();
			if(bookings != null) {
				ResponseStructure<List<Bookings>> structure = new ResponseStructure<>();
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setMessage("bookings found");
				structure.setData(bookings);
				return new ResponseEntity<ResponseStructure<List<Bookings>>>(structure,HttpStatus.FOUND);
			}
			throw new BookingNotFound("Bookings not found for the given User");
		}
		throw new UserPwdWrong("User not found for the given credentials");
	}

}
