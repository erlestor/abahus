package rest;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import core.House;
import core.User;

//RestAPI controllerclass
@RestController
public class AbahusController {

	@Autowired
	private AbahusService abahusService;

	//getter for current user that is logged in
	@GetMapping("/getUser") 
	public String getUser() {
		User user = abahusService.getUser();
		if (user == null || user.getEmail().equals("email@email.com"))
			return '"' + "null" + '"';
		return '"' + abahusService.getUser().getEmail() + '"';
	}

	//registering user and returning the email
	@PostMapping("/registerUser")
	public String registerUser(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> request = mapper.readValue(json, Map.class);
		abahusService.register(request.get("email"), request.get("password"), request.get("confirmPassword"));
		return '"' + abahusService.getMain().getCurrentUser().getEmail() + '"';
	}

	//logging in user and returning the email
	@PostMapping("/logIn")
	public String logIn(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> request = mapper.readValue(json, Map.class);
		abahusService.logIn(request.get("email"), request.get("password"));
		return '"' + abahusService.getMain().getCurrentUser().getEmail() + '"';
	}

	//removing house from a spesific location
	@DeleteMapping(value="/removeHouse/{location}")
	public String removeHouse(@PathVariable("location") String location) throws IOException {
		
		House house = abahusService.removeHouse(location);
		return '"' + house.getLocation() + "is deleted" + '"';  
	}
	
	//returns all houses
	@GetMapping("/houses")
	public HashMap<String, List<Object>> getHouses() throws JsonParseException, JsonMappingException, IOException {
		return abahusService.getHouses();
	}

	//adds house with location = location
	@PostMapping("/addHouse/{location}")
	public String addHouse(@PathVariable("location") String location) throws  IOException{
		
		abahusService.addHouse(location);
		return '"' + "'House is added'" + '"';
	}

	//sets house at spesific location available and unavailable
	@PostMapping("/setAvailable/{location}/{available}")
	public String setAvailable(@PathVariable("location") String location, @PathVariable("available") boolean available) throws  IOException{
		
		return '"' + abahusService.setAvailable(location, available) + '"';
	}

	//loging out of currentUser
	@PostMapping("/logOut")
	public String logOut(){
		abahusService.logOut();
		
		return '"' + "'You are logged out'" + '"';
	}
}