package core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HouseTest {
	private House house;
	private String location = "location";
	private User user = new User("email@email.com", "passord");

	@BeforeEach
	public void setup(){
		house = new House(location, user);
	}

	//Tester at konstruktøren funker og at hvert hus må både ha en adresse og en bruker
	@Test
	public void testConstructor(){
	    assertEquals(location, house.getLocation());
	    assertEquals(user, house.getUser());

		assertThrows(IllegalArgumentException.class, () -> {new House("", user);});
	        
		assertThrows(NullPointerException.class, () -> {new House(null, null);});
	
	}

	//Tester at Det ikke går an å gjøre samme hus utilgjengelig flere ganger
	@Test
	public void testAlreadyInUse(){
		house.setAvailable(false);

		assertThrows(IllegalArgumentException.class, () -> {house.setAvailable(false);});

	}
}