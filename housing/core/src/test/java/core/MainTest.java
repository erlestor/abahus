package core;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {
	    
	private Main main;

	@BeforeEach
	public void setup(){
	    this.main = new Main ("email@email.com", "passord", "passord");
	}


	@Test
	public void testConstructor1(){
	    assertThrows(IllegalArgumentException.class, () -> {new Main("heisann@gmail.com", "mamma", "pappa");});

	    assertThrows(IllegalArgumentException.class, () -> {new Main("email@email.com", "passord", "passord");});

	}


	@Test
	public void testConstructor2(){
	    Main main2 = new Main ("email@email.com", "passord");

	    //assertEquals("email@email.com", main2.getUser().getEmail());

	    assertThrows(IllegalStateException.class, () -> {new Main("random@gmail.com", "passord");});

	}


	@Test
	public void testHostNewHouse(){
	    main.HostNewHouse("location");

	    assertThrows(IllegalArgumentException.class, () -> {main.HostNewHouse("location");});

	}
}