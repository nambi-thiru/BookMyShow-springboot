package com.movie.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.movie.bookmyshow.dao.BookingsDao;
import com.movie.bookmyshow.dao.UserDao;
import com.movie.bookmyshow.dto.UserDto;
import com.movie.bookmyshow.entity.Bookings;
import com.movie.bookmyshow.entity.User;
import com.movie.bookmyshow.exception.BookingNotFound;
import com.movie.bookmyshow.exception.UserNotFound;
import com.movie.bookmyshow.util.ResponseStructure;

@Service
public class UserService {
	
	@Autowired
	UserDao dao;
	
	@Autowired
	BookingsDao bdao;
	
	public ResponseEntity<ResponseStructure<UserDto>> saveUser(User user){
		UserDto dto = new UserDto();
		ModelMapper mapper = new ModelMapper();
		mapper.map(dao.saveUser(user), dto);
		
		ResponseStructure<UserDto> structure = new ResponseStructure<>();
		structure.setData(dto);
		structure.setMessage("User created");
		structure.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> findUser(int userId){
		User user = dao.findUser(userId);
		if(user != null) {
			UserDto dto = new UserDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(user, dto);
			
			ResponseStructure<UserDto> structure = new ResponseStructure<>();
			structure.setData(dto);
			structure.setMessage("User Found");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.FOUND);
		}
		throw new UserNotFound("User not found for the given id"); // user not found in db.. throw exception 
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> deleteUser(int userId){
		User user = dao.findUser(userId);
		if(user != null) {
			UserDto dto = new UserDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(dao.deleteUser(userId), dto);
			
			ResponseStructure<UserDto> structure = new ResponseStructure<>();
			structure.setData(dto);
			structure.setMessage("User Deleted");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.OK);
		}
		throw new UserNotFound("User not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> updateUser(User user, int userId){
		User exisUser = dao.findUser(userId);
		if(exisUser != null) {
			UserDto dto = new UserDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(dao.updateUser(user, userId), dto);
			
			ResponseStructure<UserDto> structure = new ResponseStructure<>();
			structure.setData(dto);
			structure.setMessage("User Updated");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.OK);
		}
		throw new UserNotFound("User not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<List<UserDto>>> findAllUsers(){
		List<User> s = dao.findAllUser();
		if(s != null) {
			
			List<UserDto> dtolist = new ArrayList<>();
			ModelMapper mapper = new ModelMapper();
			
			for(User stu : dao.findAllUser()) {
				UserDto dto = new UserDto();
				mapper.map(stu ,dto);
				dtolist.add(dto);
			}
			
			ResponseStructure<List<UserDto>> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("found all Users");
			structure.setData(dtolist);
			return new ResponseEntity<ResponseStructure<List<UserDto>>>(structure, HttpStatus.FOUND);
		}
		throw new UserNotFound("User not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> findByUserMail(String userMail){
		User user = dao.findByUserMail(userMail);
		if(user != null) {
			UserDto dto = new UserDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(user, dto);
			
			ResponseStructure<UserDto> structure = new ResponseStructure<>();
			structure.setData(dto);
			structure.setMessage("User Found by MailId");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.FOUND);
		}
		throw new UserNotFound("User not found for the given mailId"); // admin not found in db.. throw exception 
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> loginUser(String userMail, String userPwd){
		User user = dao.findByUserMail(userMail);
		if(user.getUserMail().equals(userMail)) {
			if(user.getUserPwd().equals(userPwd)) {
				UserDto dto = new UserDto();
				ModelMapper mapper = new ModelMapper();
				mapper.map(user, dto);
				
				ResponseStructure<UserDto> structure = new ResponseStructure<>();
				structure.setData(dto);
				structure.setMessage("User Logged in");
				structure.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.OK);
			}
			return null;
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> assignBooking(String userMail, String userPwd, int bookingId){
		User user = dao.findByUserMail(userMail);
		if(loginUser(userMail, userPwd) != null) {
			Bookings booking = bdao.findBookings(bookingId);
			if(booking != null) {
				user.getBookings().add(booking);
				
				UserDto dto = new UserDto();
				ModelMapper mapper = new ModelMapper();
				mapper.map(dao.updateUser(user, user.getUserId()), dto);
				
				ResponseStructure<UserDto> structure = new ResponseStructure<>();
				structure.setData(dto);
				structure.setMessage("Booking assigned to user");
				structure.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.OK);
			}
			throw new BookingNotFound("Booking not found for the given id");
		}
		throw new UserNotFound("User not found for the given id");
	}

}
