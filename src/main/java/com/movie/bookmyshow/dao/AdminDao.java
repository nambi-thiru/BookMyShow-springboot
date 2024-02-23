package com.movie.bookmyshow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.bookmyshow.entity.Admin;
import com.movie.bookmyshow.repo.AdminRepo;

@Repository
public class AdminDao {
	
	@Autowired
	AdminRepo repo;
	
	public Admin saveAdmin(Admin admin) {
		return repo.save(admin);
	}
	
	public Admin findAdmin(int adminId) {
		Optional<Admin> opAdmin = repo.findById(adminId);
		if(opAdmin.isPresent()) {
			return opAdmin.get();
		}
		return null;
	}
	
	public Admin deleteAdmin(int adminId) {
		Admin admin = findAdmin(adminId);
		repo.delete(admin);
		return admin;
	}
	
	public Admin updateAdmin(Admin admin, int adminId) {
		Admin exisAdmin = findAdmin(adminId);
		if(exisAdmin != null) {
			admin.setAdminId(adminId);
			return repo.save(admin);
		}
		return null;
	}
	
	public List<Admin> findAllAdmin(){
		return repo.findAll();
	}
	
	public Admin findByAdminMail(String adminMail) {
		return repo.findByadminMail(adminMail);
	}

}
