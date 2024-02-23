package com.movie.bookmyshow.util;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class Config implements WebMvcConfigurer{
	
	@Bean
	public OpenAPI swaggerDocOpenApi() {
		Server devserver = new Server();
		devserver.setUrl("localhost:8080");
		devserver.setDescription("Development Server");
		
		Server testserver = new Server(); 
		testserver.setUrl("localhost:8081");
		testserver.setDescription("Test Server");
		
		Contact co = new Contact();
		co.setEmail("nambidravid@gmail.com");
		co.setName("Nambi");
		co.setUrl("https://github.com/nambi-thiru/BookMyShow-springboot.git");
		
		License ls = new License();
		ls.setName("License");
		ls.setUrl("License Providers");
		
		Info in = new Info();
		in.setContact(co);
		in.setLicense(ls);
		in.setDescription("BookMyShow : Movie Ticket Booking App");
		
		in.setTermsOfService("https://en.wikipedia.org/wiki/Terms_of_service");
		in.setTitle("Book My Show");
		in.setVersion("1.0");
		
		OpenAPI op = new OpenAPI();
		op.info(in);
		op.servers(Arrays.asList(devserver, testserver));
		return op;
	}

}
