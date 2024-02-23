package com.movie.bookmyshow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.bookmyshow.entity.User;
import com.movie.bookmyshow.repo.UserRepo;

@Repository
public class UserDao {
	
	@Autowired
	UserRepo repo;
	
	public User saveUser(User user) {
		return repo.save(user);
	}
	
	public User findUser(int userId) {
		Optional<User> opUser = repo.findById(userId);
		if(opUser.isPresent()) {
			return opUser.get();
		}
		return null;
	}
	
	public User deleteUser(int userId) {
		User user = findUser(userId);
		repo.delete(user);
		return user;
	}
	
	public User updateUser(User user, int userId) {
		User exisUser = findUser(userId);
		if(exisUser != null) {
			user.setUserId(userId);
			return repo.save(user);
		}
		return null;
	}
	
	public List<User> findAllUser(){
		return repo.findAll();
	}
	
	public User findByUserMail(String userMail) {
		return repo.findByuserMail(userMail);
	}

}
