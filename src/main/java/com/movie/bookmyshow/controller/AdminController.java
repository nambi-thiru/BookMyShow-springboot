package com.movie.bookmyshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.bookmyshow.dto.AdminDto;
import com.movie.bookmyshow.entity.Admin;
import com.movie.bookmyshow.service.AdminService;
import com.movie.bookmyshow.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	AdminService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(@Valid @RequestBody Admin admin, BindingResult result) {
		return service.saveAdmin(admin);
	}
	
	
	@GetMapping
	public ResponseEntity<ResponseStructure<AdminDto>> findAdmin(@Valid @RequestParam int adminId) {
		return service.findAdmin(adminId);
	}
	
	@GetMapping("adminMail")
	public ResponseEntity<ResponseStructure<AdminDto>> findAdminByMail(@Valid @RequestParam String adminMail) {
		return service.findByAdminMail(adminMail);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(@RequestParam int adminId) {
		return service.deleteAdmin(adminId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(@Valid @RequestBody Admin admin,@RequestParam int adminId) {
		return service.updateAdmin(admin, adminId);
	}
	
	@GetMapping("login")
	public ResponseEntity<ResponseStructure<AdminDto>> loginAdmin(@RequestParam String adminMail, @RequestParam String adminPwd) {
		return service.loginAdmin(adminMail, adminPwd);
	}
	
	@GetMapping("assigntheatre")
	public ResponseEntity<ResponseStructure<AdminDto>> assignTheatre(@RequestParam String adminMail,@RequestParam String adminPwd,@RequestParam int theatreId) {
		return service.assignTheatre(adminMail, adminPwd, theatreId);
	}

}
