package core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HouseTest {
	private House house;
	private String location = "location";
	private User user = new User("email@email.com", "passord");

	@Test
	public void testConstructor(){
	    house = new House(location, user);
	    assertEquals(location, house.getLocation());
	    assertEquals(user, house.getUser());

	    assertThrows(IllegalArgumentException.class, () -> {new House("", user);});
	        
	    assertThrows(NullPointerException.class, () -> {new House(null, null);});
	}
}