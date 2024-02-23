package com.movie.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.bookmyshow.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	public User findByuserMail(String userMail);

}
