package com.rest.restapi;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import core.Main;
import core.House;
import core.User; 
import jsonworker.Jsonworker; 
import java.util.ArrayList;


@SpringBootApplication
@RestController
public class RestApplication {

	private Jsonworker jsonworker; 


	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@GetMapping("/registerUser")
	public String registerUser(@RequestParam(value = "email", defaultValue = "email") String email) throws JsonParseException, JsonMappingException, IOException {
		Main m = new Main("1@2345.com", "123", "123");
		return String.format("user: %s!", m.getCurrentUser());
	}
 
	@GetMapping("/logIn")
	public String logIn(@RequestParam(value = "email", defaultValue = "email") String email) throws JsonParseException, JsonMappingException, IOException {
		Main m = new Main("1@2.com", "123");
		return String.format("user: %s!", m.getCurrentUser());
	}


	/*
	//funker ikke
	@DeleteMapping("/removeHouse")
	public String removeHouse(@RequestParam(value= "house", defaultValue= "house??") String location, String email, String password ) throws IOException {
		House house = jsonworker.removeHouse(location, email);
		return house + "is deleted";  
	}
	*/

	@GetMapping("/houses")
	public ArrayList<House> getHouses() throws JsonParseException, JsonMappingException, IOException {
		ArrayList<House> allHouses = jsonworker.getAllHouses();
		return allHouses; 
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