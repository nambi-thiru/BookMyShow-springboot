package com.movie.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.movie.bookmyshow.dao.AdminDao;
import com.movie.bookmyshow.dao.TheatreDao;
import com.movie.bookmyshow.dto.AdminDto;
import com.movie.bookmyshow.entity.Admin;
import com.movie.bookmyshow.entity.Theatre;
import com.movie.bookmyshow.exception.AdminNotFound;
import com.movie.bookmyshow.exception.TheatreNotFound;
import com.movie.bookmyshow.util.ResponseStructure;

@Service
public class AdminService {
	
	@Autowired
	AdminDao dao; 
	
	@Autowired
	TheatreDao tdao;
	
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(Admin admin){
		AdminDto dto = new AdminDto();
		ModelMapper mapper = new ModelMapper();
		mapper.map(dao.saveAdmin(admin), dto);
		
		ResponseStructure<AdminDto> structure = new ResponseStructure<>();
		structure.setData(dto);
		structure.setMessage("Admin created");
		structure.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> findAdmin(int adminId){
		Admin admin = dao.findAdmin(adminId);
		if(admin != null) {
			AdminDto dto = new AdminDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(admin, dto);
			
			ResponseStructure<AdminDto> structure = new ResponseStructure<>();
			structure.setData(dto);
			structure.setMessage("Admin Found");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FOUND);
		}
		throw new AdminNotFound("Admin not found for the given id"); // admin not found in db.. throw exception 
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(int adminId){
		Admin admin = dao.findAdmin(adminId);
		if(admin != null) {
			AdminDto dto = new AdminDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(dao.deleteAdmin(adminId), dto);
			
			ResponseStructure<AdminDto> structure = new ResponseStructure<>();
			structure.setData(dto);
			structure.setMessage("Admin Deleted");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
		}
		throw new AdminNotFound("Admin not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(Admin admin, int adminId){
		Admin exisAdmin = dao.findAdmin(adminId);
		if(exisAdmin != null) {
			AdminDto dto = new AdminDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(dao.updateAdmin(admin, adminId), dto);
			
			ResponseStructure<AdminDto> structure = new ResponseStructure<>();
			structure.setData(dto);
			structure.setMessage("Admin Updated");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
		}
		throw new AdminNotFound("Admin not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<List<AdminDto>>> findAllAdmins(){
		List<Admin> s = dao.findAllAdmin();
		if(s != null) {
			
			List<AdminDto> dtolist = new ArrayList<>();
			ModelMapper mapper = new ModelMapper();
			
			for(Admin stu : dao.findAllAdmin()) {
				AdminDto dto = new AdminDto();
				mapper.map(stu ,dto);
				dtolist.add(dto);
			}
			
			ResponseStructure<List<AdminDto>> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("found all Admins");
			structure.setData(dtolist);
			return new ResponseEntity<ResponseStructure<List<AdminDto>>>(structure, HttpStatus.FOUND);
		}
		throw new AdminNotFound("Admin not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> findByAdminMail(String adminMail){
		Admin admin = dao.findByAdminMail(adminMail);
		if(admin != null) {
			AdminDto dto = new AdminDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(admin, dto);
			
			ResponseStructure<AdminDto> structure = new ResponseStructure<>();
			structure.setData(dto);
			structure.setMessage("Admin Found by MailId");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FOUND);
		}
		throw new AdminNotFound("Admin not found for the given id"); // admin not found in db.. throw exception 
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> loginAdmin(String adminMail, String adminPwd){
		Admin admin = dao.findByAdminMail(adminMail);
		if(admin != null) {
			if(admin.getAdminMail().equals(adminMail)) {
				if(admin.getAdminPwd().equals(adminPwd)) {
					AdminDto dto = new AdminDto();
					ModelMapper mapper = new ModelMapper();
					mapper.map(admin, dto);
					
					ResponseStructure<AdminDto> structure = new ResponseStructure<>();
					structure.setData(dto);
					structure.setMessage("Admin Logged in");
					structure.setStatus(HttpStatus.OK.value());
					return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
				}
				throw new AdminNotFound("check admin mail");
			}
			return null;
		}
		throw new AdminNotFound("Admin not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> assignTheatre(String adminMail, String adminPwd, int theatreId){
		Admin admin = dao.findByAdminMail(adminMail);
		if(loginAdmin(adminMail, adminPwd) != null) {
			Theatre theatre = tdao.findTheatre(theatreId);
			if(theatre != null) {
				admin.getTheatre().add(theatre);
				
				AdminDto dto = new AdminDto();
				ModelMapper mapper = new ModelMapper();
				mapper.map(dao.updateAdmin(admin, admin.getAdminId()), dto);
				
				ResponseStructure<AdminDto> structure = new ResponseStructure<>();
				structure.setData(dto);
				structure.setMessage("Theatre assigned to admin");
				structure.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
			}
			throw new TheatreNotFound("Theatre not found for the given id");
		}
		throw new AdminNotFound("Admin not found for the given id");
	}

}
