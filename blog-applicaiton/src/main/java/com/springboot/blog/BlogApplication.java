package com.springboot.blog;

import com.springboot.blog.payloads.ErrorDetail;
import com.springboot.blog.payloads.PostResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;


@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

//	@Bean
//	public PostResponse  postResponse() { return  new PostResponse();}

//	@Bean
//	public ErrorDetail  errorDetail(){
//		return new ErrorDetail();
//	}



}
