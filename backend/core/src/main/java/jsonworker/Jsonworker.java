package jsonworker;

import core.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;

public class Jsonworker {
    private final static String userHome = System.getProperty("user.home") + "/gr2129/";
    private final static Path dirPath = Paths.get(userHome).toAbsolutePath();
    private final static String housePath = "data/houses.json";
    private final static String userPath = "data/users.json";
    private final static ObjectMapper mapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        return defaultObjectMapper;
    }

    private <T> ArrayList<T> readFileAsArray(String path_string, Class<T[]> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        return new ArrayList<T>(Arrays.asList(mapper.readValue(dirPath.resolve(path_string).toFile(), clazz)));
    }

    private <T> void writeFile(String path, ArrayList<T> data) throws IOException {
        File file = new File(dirPath.resolve(path).toString());
        FileWriter fileWriter = new FileWriter(file, false);
        SequenceWriter writer = mapper.writer().writeValues(fileWriter);

        writer.write(data);
        writer.close();
    }

    public ArrayList<User> getAllUsers() throws JsonParseException, JsonMappingException, IOException {
        return readFileAsArray(userPath, User[].class);
    }

    private User getUser(String email) throws JsonParseException, JsonMappingException, IOException {
        ArrayList<User> users = getAllUsers();

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }

        return null;
    }

    public boolean checkPassword(String email, String pwd) throws IOException {
        ArrayList<User> users = getAllUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user.getPassword().equals(pwd);
            }
        }
        return false;
    }

    public ArrayList<House> getAllHouses() throws JsonParseException, JsonMappingException, IOException {
        return readFileAsArray(housePath, House[].class);
    }

    public User addUser(String email, String pwd) throws IOException, IllegalArgumentException {
        ArrayList<User> users = getAllUsers();

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                throw new IllegalArgumentException("user already exists");
            }
        }

        User user = new User(email, pwd);
        users.add(user);
        writeFile(userPath, users);

        return user;
    }

    public User removeUser(String email) throws IOException {
        ArrayList<User> users = getAllUsers();

        Boolean found = false;
        User user = null;

        for (User _user : users) {
            if (_user.getEmail().equals(email)) {
                found = true;
                user = _user;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("user doesn't exists");
        }

        users.remove(user);
        writeFile(userPath, users);
        return user;
    }

    public House addHouse(String location, String userEmail)
            throws JsonParseException, JsonMappingException, IOException, IllegalArgumentException {
        User user;
        try {
            user = getUser(userEmail);

        } catch (Exception e) {
            throw new IllegalArgumentException("user does not exist");
        }
        ArrayList<House> houses = getAllHouses();

        for (House house : houses) {
            if (house.getLocation().equals(location) && house.getUser().getEmail().equals(userEmail)) {
                throw new IllegalArgumentException("House already exists");
            }
        }

        House house = new House(location, user);
        houses.add(house);
        writeFile(housePath, houses);
        return house;
    }

    public House removeHouse(String location, String userEmail) throws IOException {
        ArrayList<House> houses = getAllHouses();
        Boolean found = false;
        House house = null;

        for (House _house : houses) {
            if (_house.getLocation().equals(location) && _house.getUser().getEmail().equals(userEmail)) {
                found = true;
                house = _house;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("house and user combo doesn't exists");
        }

        houses.remove(house);
        writeFile(housePath, houses);
        return house;
    }
}
