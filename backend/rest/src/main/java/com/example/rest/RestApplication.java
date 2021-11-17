package com.rest.restapi;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import core.Main;
import core.House;
import core.User; 
import jsonworker.Jsonworker; 
import java.util.ArrayList;


@SpringBootApplication
@RestController
public class RestApplication {

	private Jsonworker jsonworker; 
	private User user;


	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@GetMapping("/users")
	public ArrayList<User> getUsers() throws JsonParseException, JsonMappingException, IOException {
		ArrayList<User> allUsers = jsonworker.getAllUsers();
		return allUsers; 
	}

	@GetMapping("/registerUser")
	public User registerUser(@RequestParam(value = "email", defaultValue = "email") String email) throws JsonParseException, JsonMappingException, IOException {
		Main m = new Main("1@234.com", "123", "123");
		this.user = m.getCurrentUser();
		return m.getCurrentUser();
		//String.format("user: %s!", m.getCurrentUser());
	}
 
	@GetMapping("/logIn")
	public User logIn(@RequestParam(value = "email", defaultValue = "email") String email) throws JsonParseException, JsonMappingException, IOException {
		Main m = new Main("1@2345.com", "123");
		this.user = m.getCurrentUser();
		return m.getCurrentUser();
		//String.format("user: %s!", m.getCurrentUser());
	}

	//denne metoden funker, men vi trenger noen feilmeldinger inni her et sted
	@ResponseBody @RequestMapping(value="/removeHouse/{location}", method=RequestMethod.GET)
	public String removeHouse(@PathVariable("location") String location, @RequestBody User user) throws IOException {
	
		String l = location.replace('_', ' ');

		ArrayList<House> houses = Jsonworker.getAllHouses();
		List<House> hStream = houses.stream().filter(house -> house.getLocation().equals(l))
				.collect(Collectors.toList());
			if (hStream.size() < 1) {
				return "Could not be deleted";
			}

			House h = hStream.get(0);

			if (!h.getUser().equals(user)){
				return "The house is not registered to this user";
			}

			Jsonworker.removeHouse(l, user.getEmail());
			return h.toString() + "is deleted";  
	}

	@RequestMapping(value = "/house/{location}", method=RequestMethod.GET)
	public String getHouse(@PathVariable("location") String location) throws IOException {

		String l = location.replace('_', ' ');
		
		ArrayList<House> houses = Jsonworker.getAllHouses();
		List<House> hStream = houses.stream().filter(house -> house.getLocation().equals(l))
				.collect(Collectors.toList());
			if (hStream.size() < 1) {
				return "can not get House";
			}

			House h = hStream.get(0);
			return h.toString(); 
	} 

	
	@GetMapping("/houses")
	public ArrayList<House> getHouses() throws JsonParseException, JsonMappingException, IOException {
		ArrayList<House> allHouses = jsonworker.getAllHouses();
		return allHouses; 
	}

	
	@ResponseBody @RequestMapping("/addHouse/{location}")
	public String addHouse(@PathVariable("location") String location, @RequestBody User user) throws  IOException{
		House h = jsonworker.addHouse(location , user.getEmail());
		return (h.toString() + "is added"); 
	}
	


}