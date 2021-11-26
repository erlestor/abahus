package rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.stereotype.Service;

import core.Main;
import core.House;

@Service
public class AbahusService {
    
    private Main main;

    public AbahusService() throws JsonParseException, JsonMappingException, IOException{
       this.main = new Main();
    }

    //enkapsuleres
    public void logIn(String email, String passord){
        this.main.logInUser(email, passord);
    }

    //enkapsuleres 
    public void register(String email, String passord, String confirmPassord) throws IllegalArgumentException, IOException{
        this.main.registerUser(email, passord, confirmPassord);
    }

    public House removeHouse(String location) throws IOException{
        if (getMain() == null || getMain().getCurrentUser() == null){
			throw new IllegalStateException("You are not logged in");
		}

		String l = location.replace('_', ' ');

		List<House> houses = getMain().getHousing();
		List<House> hStream = houses.stream().filter(house -> house.getLocation().equals(l))
				.collect(Collectors.toList());
		if (hStream.size() < 1) {
            throw new IllegalStateException("House could not be deleted");
		}

		House h = hStream.get(0);
			
		if (!h.getUser().getEmail().equals(getMain().getCurrentUser().getEmail())){
            throw new IllegalStateException("The house is not registered to this user");
		}

		getMain().removeHouse(h);

        return h;

    }

    public House getHouse(String location){
        String l = location.replace('_', ' ');
		
		List<House> houses = getMain().getHousing();
		List<House> hStream = houses.stream().filter(house -> house.getLocation().equals(l))
				.collect(Collectors.toList());
		if (hStream.size() < 1) {
            throw new IllegalArgumentException("House is not registered");
		}

		return hStream.get(0);
    }


    public HashMap<String, List<Object>> getHouses(){
        List<House> allHouses = getMain().getHousing();

		HashMap<String, List<Object>> houseMap = new HashMap<String, List<Object>>();

		for (House h: allHouses){
			List<Object> houseProperties= new ArrayList<Object>();
			houseProperties.add(h.getUser().getEmail());
			houseProperties.add(h.isAvailable());

			houseMap.put(h.getLocation(), houseProperties);
		}
		return houseMap; 
    }


    public String addHouse(String location) throws JsonParseException, JsonMappingException, IllegalArgumentException, IOException{
        String l = location.replace('_', ' ');
		
		if (getMain() == null || getMain().getCurrentUser() == null){
			throw new IllegalStateException("You are not logged in");
		}
		
        getMain().hostNewHouse(l);

        return l;
    }


    public String setAvailable(String location, boolean isAvailable){
        if (getMain() == null || getMain().getCurrentUser() == null){
			throw new IllegalStateException("You are not logged in");
		}
       
        List<House> allHouses = getMain().getHousing();

		String l = location.replace('_', ' ');

        boolean altered = false;

		for (House h: allHouses){
			if (h.getLocation().equals(l) && h.getUser().getEmail().equals(getMain().getCurrentUser().getEmail())){
				h.setAvailable(isAvailable);
                altered = true;
			}
		}

        if (!altered){
            throw new IllegalStateException("House does not excist or it is not your house");
        }   

        return '"' + "'House is altered'" + '"';
    }


    public Main getMain(){
        return this.main;
    }

}
