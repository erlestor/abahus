package jsonworker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.*;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.junit.jupiter.api.Test;

public class JsonworkerTest {

    private static Jsonworker worker = new Jsonworker();

    @Test
    public void checkPassword() throws IOException {
        System.out.println("Asserting equality for test email netteland97@gmail.com");
        assertEquals(worker.checkPassword("netteland97@gmail.com", "123"), true);
    }

    @Test
    public void getHouses() throws JsonParseException, JsonMappingException, IOException {
        System.out.println("Reading all houses...");
        worker.getAllHouses();
    }

    @Test
    public void addUser() throws IOException {
        System.out.println("Adding user...");
        worker.addUser("testing@gmail.com", "123");
    }

    @Test
    void removeUser() throws IOException {
        System.out.println("Removing user...");
        worker.removeUser("testing@gmail.com");
    }

    @Test
    public void noDuplicateUsers() throws JsonParseException, JsonMappingException, IOException {
        System.out.println("Doesn't write duplicate users...");
        ArrayList<User> beforeUsers = worker.getAllUsers();

        try {
            User samePwd = worker.addUser("netteland97@gmail.com", "123");
            User otherPwd = worker.addUser("netteland97@gmail.com", "pass");
            assertEquals(samePwd, null);
            assertEquals(otherPwd, null);

        } catch (Exception e) {
        }
        ArrayList<User> afterUsers = worker.getAllUsers();
        assertEquals(beforeUsers.size(), afterUsers.size());
    }

    @Test
    public void addHouseAndRemoveHouse() throws JsonParseException, JsonMappingException, IOException {
        System.out.println("Adding house...");
        worker.addHouse("Min Gate 4", "netteland97@gmail.com");
        assertThrows(IllegalArgumentException.class, () -> {
            worker.addHouse("Min Gate 4", "netteland97@gmail.com");
        });

        System.out.println("Removing house...");
        worker.removeHouse("Min Gate 4", "netteland97@gmail.com");
        assertThrows(IllegalArgumentException.class, () -> {
            worker.removeHouse("Min Gate 4", "netteland97@gmail.com");
        });
    }

    @Test
    public void testSetAvailableHouse() throws IOException {
        worker.addHouse("Min Gate 4", "netteland97@gmail.com");

        worker.setAvailableHouse("Min Gate 4", false, "netteland97@gmail.com");
        assertThrows(IllegalArgumentException.class, () -> {
            worker.setAvailableHouse("Min Gate 4", false, "netteland97@gmail.com");
        });

        worker.setAvailableHouse("Min Gate 4", true, "netteland97@gmail.com");

        worker.removeHouse("Min Gate 4", "netteland97@gmail.com");
        System.out.println("House can be set as available and unavailable");
    }

    @Test
    public void noDuplicateHouses() throws JsonParseException, JsonMappingException, IOException {
        System.out.println("Doesn't write duplicate houses...");
        ArrayList<House> beforeHouses = worker.getAllHouses();

        try {
            House dupHouse = worker.addHouse("Oscar Wistings vei 6", "netteland97@gmail.com");
            assertEquals(dupHouse, null);
        } catch (Exception e) {
        }
        ArrayList<House> afterHouses = worker.getAllHouses();
        assertEquals(beforeHouses.size(), afterHouses.size());
    }
}
