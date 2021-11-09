package com.rest.restapi;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import core.Main;



@SpringBootApplication
@RestController
public class RestApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) throws JsonParseException, JsonMappingException, IOException {
		Main m = new Main("1@2.com", "123", "123");
		return String.format("Hello %s!", m.getCurrentUser());
	}

}
