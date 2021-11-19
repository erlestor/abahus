package rest;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.stream.Collectors;

import core.Main;
import core.House;
import core.User; 


@SpringBootApplication
@RestController
public class RestApplication {

	private Main m;

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}



	@GetMapping("/registerUser")
	public User registerUser(@RequestParam(value = "email", defaultValue = "email") String email) throws JsonParseException, JsonMappingException, IOException {
		this.m = new Main("1@234.com", "123", "123");
		return this.m.getCurrentUser();
	}
 
	@GetMapping("/logIn")
	public User logIn(@RequestParam(value = "email", defaultValue = "email") String email) throws JsonParseException, JsonMappingException, IOException {
		this.m = new Main("1@2345.com", "123");
		return m.getCurrentUser();
		
	}

	@ResponseBody @RequestMapping(value="/removeHouse/{location}", method=RequestMethod.GET)
	public String removeHouse(@PathVariable("location") String location) throws IOException {
		if (this.m == null){
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
	public List<House> getHouses() throws JsonParseException, JsonMappingException, IOException {
		List<House> allHouses = m.getHousing();
		return allHouses; 
	}

	
	@ResponseBody @RequestMapping("/addHouse/{location}")
	public String addHouse(@PathVariable("location") String location) throws  IOException{
		if (this.m == null){
			return "You are not logged in";
		}
		m.hostNewHouse(location);
		return "House is added";
	}

	@ResponseBody @RequestMapping(value="/removeUser", method=RequestMethod.GET)
    public String removeUser() throws IOException {

        if(this.m == null){
            return "you are not logged in";
        }
        m.removeUser(m.getCurrentUser());
        return m.getCurrentUser().toString() + "is deleted";  
    }

	


}