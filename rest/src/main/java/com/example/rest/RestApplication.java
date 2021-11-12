package com.example.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;


@SpringBootApplication
@RestController
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	


/*
	//funnker ikke
	@PostMapping(path = "/addHouse", 
			consumes = MediaType.APPLICATION_JSON_VALUE
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<House> addNewHouse(@RequestBody House newHouse) throws JsonParseException, JsonMappingException, IOException, IllegalArgumentException {
		House house = 
		jsonworker.addHouse(location, email);
	}

*/

}