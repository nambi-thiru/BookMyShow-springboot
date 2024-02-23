package com.movie.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.bookmyshow.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{
	
	public Admin findByadminMail(String adminMail);

}
