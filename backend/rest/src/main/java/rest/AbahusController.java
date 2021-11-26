package rest;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import core.House;


@RestController
public class AbahusController {

	@Autowired
	private AbahusService abahusService;

	@PostMapping("/registerUser")
	public String registerUser(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> request = mapper.readValue(json, Map.class);
		abahusService.register(request.get("email"), request.get("password"), request.get("confirmPassword"));
		return '"' + abahusService.getMain().getCurrentUser().getEmail() + '"';
	}

	@PostMapping("/logIn")
	public String logIn(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> request = mapper.readValue(json, Map.class);
		abahusService.logIn(request.get("email"), request.get("password"));
		return '"' + abahusService.getMain().getCurrentUser().getEmail() + '"';
	}

	@DeleteMapping(value="/removeHouse/{location}")
	public String removeHouse(@PathVariable("location") String location) throws IOException {
		
		House house = abahusService.removeHouse(location);
		return house.getLocation() + "is deleted";  
	}

	//return map with house and isAvailable
	@RequestMapping(value = "/getHouse/{location}", method=RequestMethod.GET)
	public String getHouse(@PathVariable("location") String location) throws IOException {
		
		House house = abahusService.getHouse(location);
		return house.getLocation() + " " + 
			String.valueOf(house.isAvailable()) + " " + house.getUser().getEmail(); 
	} 

	
	@GetMapping("/houses")
	public HashMap<String, List<Object>> getHouses() throws JsonParseException, JsonMappingException, IOException {
		return abahusService.getHouses();
	}

	
	@PostMapping("/addHouse/{location}")
	public String addHouse(@PathVariable("location") String location) throws  IOException{
		
		abahusService.addHouse(location);
		return '"' + "'House is added'" + '"';
	}


	@PostMapping("/setAvailable/{location}/{available}")
	public String setAvailable(@PathVariable("location") String location, @PathVariable("available") boolean available) throws  IOException{
		
		return abahusService.setAvailable(location, available);
	}

	//må legge til en logUt-metode
}