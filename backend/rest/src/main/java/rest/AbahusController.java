package rest;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

import core.*;


@RestController
public class AbahusController {

	@Autowired
	private AbahusService abahusService;
	private Main m;

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
		if (this.m == null || this.m.getCurrentUser() == null){
			return "You are not logged in";
		}

		String l = location.replace('_', ' ');

		List<House> houses = m.getHousing();
		List<House> hStream = houses.stream().filter(house -> house.getLocation().equals(l))
				.collect(Collectors.toList());
			if (hStream.size() < 1) {
				return "Could not be deleted";
			}

			House h = hStream.get(0);
			
			if (!h.getUser().getEmail().equals(m.getCurrentUser().getEmail())){
				return "The house is not registered to this user";
			}

			m.removeHouse(h);
			return h.toString() + "is deleted";  
	}

	@RequestMapping(value = "/getHouse/{location}", method=RequestMethod.GET)
	public String getHouse(@PathVariable("location") String location) throws IOException {
		this.m = new Main();

		String l = location.replace('_', ' ');
		
		List<House> houses = m.getHousing();
		List<House> hStream = houses.stream().filter(house -> house.getLocation().equals(l))
				.collect(Collectors.toList());
		if (hStream.size() < 1) {
			return "House is not registered";
		}

		House h = hStream.get(0);
		return h.toString(); 
	} 

	
	@GetMapping("/houses")
	public HashMap<String, List<Object>> getHouses() throws JsonParseException, JsonMappingException, IOException {
		//this.m = new Main();
		List<House> allHouses = m.getHousing();

		HashMap<String, List<Object>> houseMap = new HashMap<String, List<Object>>();

		for (House h: allHouses){
			List<Object> houseProperties= new ArrayList<Object>();
			houseProperties.add(h.getUser().getEmail());
			houseProperties.add(h.isAvailable());

			houseMap.put(h.getLocation(), houseProperties);
		}
		return houseMap; 
	}

	
	@PostMapping("/addHouse/{location}")
	public String addHouse(@PathVariable("location") String location) throws  IOException{
		String l = location.replace('_', ' ');
		
		if (this.m == null || this.m.getCurrentUser() == null){
			throw new IllegalStateException("You are not logged in");
		}
		m.hostNewHouse(l);
		return '"' + "'House is added'" + '"';
	}

	//Ikke enkapsulert
	@PostMapping("/setAvailable/{location}/{available}")
	public String setAvailable(@PathVariable("location") String location, @PathVariable("available") boolean available) throws  IOException{
		this.m = new Main();
		List<House> allHouses = m.getHousing();

		String l = location.replace('_', ' ');

		for (House h: allHouses){
			if (h.getLocation().equals(l)){
				h.setAvailable(available);
			}
		}
		return '"' + "'House is altered'" + '"';
	}

}