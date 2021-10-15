package jsonworker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import org.junit.jupiter.api.Test;

public class JsonworkerTest {
    

    @Test
    public void checkPassword() throws IOException {
        System.out.println("Asserting equality for test email netteland97@gmail.com");
        assertEquals(Jsonworker.checkPassword("netteland97@gmail.com", "123"), true);
    }

    @Test
    public void getHouses() throws JsonParseException, JsonMappingException, IOException {
        System.out.println("Reading all houses...");
        Jsonworker.getAllHouses();
    }

    @Test
    public void addUser() throws IOException {
        System.out.println("Adding user...");
        Jsonworker.addUser("test@gmail.com", "123");
    }

    @Test void removeUser() throws IOException {
        System.out.println("Removing user...");
        Jsonworker.removeUser("test@gmail.com");
    }

    @Test
    public void noDuplicateUsers() throws JsonParseException, JsonMappingException, IOException {
        System.out.println("Doesn't write duplicate users...");
        ArrayList<User> beforeUsers = Jsonworker.getAllUsers();

        try {
            User samePwd = Jsonworker.addUser("netteland97@gmail.com", "123");
            User otherPwd = Jsonworker.addUser("netteland97@gmail.com", "pass");
            assertEquals(samePwd, null);
            assertEquals(otherPwd, null);

        } catch (Exception e) {}
        ArrayList<User> afterUsers = Jsonworker.getAllUsers();
        assertEquals(beforeUsers.size(), afterUsers.size());
    }

    @Test
    public void addHouse() throws JsonParseException, JsonMappingException, IOException {
        System.out.println("Adding house...");
        Jsonworker.addHouse("Min Gate 4", "netteland97@gmail.com");
    }

    @Test
    public void removeHouse() throws IOException {
        System.out.println("Removing house...");
        Jsonworker.removeHouse("Min Gate 4", "netteland97@gmail.com");
    }

    @Test
    public void noDuplicateHouses() throws JsonParseException, JsonMappingException, IOException {
        System.out.println("Doesn't write duplicate houses...");
        ArrayList<House> beforeHouses = Jsonworker.getAllHouses();
        
        try {
            House dupHouse = Jsonworker.addHouse("Oscar Wistings vei 6", "netteland97@gmail.com");
            assertEquals(dupHouse, null);
        } catch (Exception e) {}
        ArrayList<House> afterHouses = Jsonworker.getAllHouses();
        assertEquals(beforeHouses.size(), afterHouses.size());
    }
}
