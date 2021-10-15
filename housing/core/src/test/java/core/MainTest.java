package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {
	//Tester ikke gettere og settere og helt enkle funksjoner implementert i java

	private Main main;

	@BeforeEach
	public void setup() throws JsonParseException, JsonMappingException, IOException{
	    this.main = new Main ("email@email.com", "passord");
	}

	//Sjekker at passord må være likt og at man ikke kan registrere en bruker som allerede eksisterer 
	@Test
	public void testConstructor1(){
		assertThrows(IllegalArgumentException.class, () -> {new Main("heisann@gmail.com", "mamma", "pappa");});
		assertThrows(IllegalStateException.class, () -> {new Main("netteland97@gmail.com", "passord", "passord");});

	}

	//Sjekker at login funker slik det skal og at man ikke kan logge inn med en bruker som ikke finnes
	@Test
	public void testConstructor2() throws JsonParseException, JsonMappingException, IOException{
		Main main2 = new Main ("netteland97@gmail.com", "123");

		assertEquals("netteland97@gmail.com", main2.getCurrentUser().getEmail());

		assertThrows(IllegalStateException.class, () -> {new Main("random@gmail.com", "passord");});

	}

	//Sjekker at man ikke kan legge ut et hus man allerede har lagt ut
	@Test
	public void testCantHostSameHouse() throws JsonParseException, JsonMappingException, IllegalArgumentException, IOException{
		assertThrows(IllegalArgumentException.class, () -> {main.hostNewHouse("location");});

	}

}