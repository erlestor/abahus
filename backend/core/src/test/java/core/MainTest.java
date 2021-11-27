package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {
	// Tester ikke gettere og settere og helt enkle funksjoner implementert i java
	// Tester heller ikke metodene kun bruker json-metoder som testes i jsonworker

	private Main main;
	private Main mainNoUser;

	@BeforeEach
	public void setup() throws JsonParseException, JsonMappingException, IOException {
		this.main = new Main("email@email.com", "passord");
		this.mainNoUser = new Main();
	}

	// Sjekker at passord må være likt og at man ikke kan registrere en bruker som
	// allerede eksisterer
	// Sjekker også at når man logger inn så logger man inn på riktig bruker (så
	// fjernes brukeren slik at testen kan kjøres flere ganger)
	@Test
	public void testConstructor1() throws JsonParseException, JsonMappingException, IOException {
		assertThrows(IllegalArgumentException.class, () -> {
			new Main("heisann@gmail.com", "mamma", "pappa");
		});
		assertThrows(IllegalStateException.class, () -> {
			new Main("netteland97@gmail.com", "passord", "passord");
		});

		Main main1 = new Main("hei@hei.com", "passord", "passord");

		assertEquals("hei@hei.com", main1.getCurrentUser().getEmail());

		main1.removeUser();
	}

	// Sjekker at login funker slik det skal og at man ikke kan logge inn med en
	// bruker som ikke finnes
	@Test
	public void testConstructor2() throws JsonParseException, JsonMappingException, IOException {

		assertEquals("email@email.com", main.getCurrentUser().getEmail());

		assertThrows(IllegalStateException.class, () -> {
			new Main("random@gmail.com", "passord");
		});
	}

	// Tester at man ikke kan logge in når man allerede er logget inn
	// og at man kan logge inn når man ikke er logget inn
	@Test
	public void testLogIn() {
		// sjekker med riktig passord og allerede logget inn
		assertThrows(IllegalStateException.class, () -> {
			main.logInUser("netteland97@gmail.com", "123");
			;
		});

		// sjekker med feil passord og ingen bruker logget inn
		assertThrows(IllegalStateException.class, () -> {
			main.logInUser("email@email.com", "password");
			;
		});
		mainNoUser.logInUser("email@email.com", "passord");
		assertEquals("email@email.com", main.getCurrentUser().getEmail());

	}

	// Sjekker at man ikke kan be om sin hus når man ikke er logget inn
	// Sjekker at du eier alle hus som kommer i lista myHouses
	@Test
	public void testGetMyHouses() {
		assertThrows(IllegalStateException.class, () -> {
			mainNoUser.getMyHouses();
			;
		});

		List<House> myHouses = main.getMyHouses();
		if (myHouses.size() > 0) {
			for (House h : myHouses) {
				assertEquals(h.getUser().getEmail(), main.getCurrentUser().getEmail());
			}
		}
	}

	@Test
	public void testGetAvailableHousing() {
		List<House> availableHouses = main.getAvailableHousing();

		if (availableHouses.size() > 0) {
			for (House h : availableHouses) {
				assertTrue(h.isAvailable());
			}
		}
	}

	@Test
	public void testHostNewHouseNoFiles()
			throws JsonParseException, JsonMappingException, IllegalArgumentException, IOException {
		assertThrows(IllegalStateException.class, () -> {
			mainNoUser.hostNewHouse("Gloshausen");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			main.hostNewHouse("location");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			main.hostNewHouse("Oscar Wistings vei 6");
		});
	}

	@Test
	public void testHostAndRemoveHouseFiles()
			throws JsonParseException, JsonMappingException, IllegalArgumentException, IOException {

		main.hostNewHouse("Gloshaugen");

		assertEquals(1, main.getHousing().stream().filter(h -> h.getLocation().equals("Gloshaugen"))
				.collect(Collectors.toList()).size());

		List<House> availableHouses = getAllAvailableHouses();

		List<House> myHouses = availableHouses.stream()
				.filter(h -> h.getUser().getEmail().equals(main.getCurrentUser().getEmail()))
				.collect(Collectors.toList());

		House house = null;
		for (House h : myHouses) {
			if (h.getLocation().equals("Gloshaugen")) {
				house = h;
			}
		}

		main.removeHouse(house);

	}

	@Test
	public void testRemoveHouseNoFiles() throws IOException {

		List<House> availableHouses = getAllAvailableHouses();

		if (availableHouses.size() > 0) {
			assertThrows(IllegalStateException.class, () -> {
				mainNoUser.removeHouse(availableHouses.get(0));
			});
		}

		List<House> notMyHouses = availableHouses.stream()
				.filter(h -> !h.getUser().getEmail().equals(main.getCurrentUser().getEmail()))
				.collect(Collectors.toList());

		if (notMyHouses.size() > 0) {
			assertThrows(IllegalArgumentException.class, () -> {
				main.removeHouse(notMyHouses.get(0));
			});
		}

	}

	@Test
	public void testRemoveUser()
			throws JsonParseException, JsonMappingException, IllegalArgumentException, IOException {
		assertThrows(IllegalStateException.class, () -> {
			mainNoUser.removeUser();
		});

		Main main2 = new Main("h@e.ii", "hei", "hei");
		main2.hostNewHouse("Ikke et hus");
		main2.removeUser();

		List<House> allHouses = main.getHousing();
		for (House h : allHouses) {
			assertNotEquals("h@e.i", h.getUser().getEmail());
		}
	}

	@Test
	public void testLogOut() {
		assertThrows(IllegalStateException.class, () -> {
			mainNoUser.logOut();
		});
		assertNotNull(this.main.getCurrentUser());
		this.main.logOut();
		assertNull(this.main.getCurrentUser());

		assertThrows(IllegalStateException.class, () -> {
			main.getMyHouses();
		});
	}

	private List<House> getAllAvailableHouses() {
		return main.getHousing().stream().filter(house -> house.isAvailable()).collect(Collectors.toList());
	}

}