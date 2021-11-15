package com.stage.WebApp21;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApp21Application {

	/*
	@Autowired
	private CustomProperties props;
	*/
	public static void main(String[] args) {
		SpringApplication.run(WebApp21Application.class, args);
	}
	

	/*
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(props.getApiUrl());
	}
	*/

}
