package jsonworker;

import core.House;
import core.User;
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

    private static Path dirPath = Paths.get("").toAbsolutePath();
    private static String housePath = "data/houses.json";
    private static String userPath = "data/users.json";
    private static ObjectMapper mapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        return defaultObjectMapper;
    }
    
    //method for reading files as array
    private static <T> ArrayList<T> readFileAsArray (String path_string, Class<T[]> clazz) throws JsonParseException, JsonMappingException, IOException {
        return new ArrayList<T>(Arrays.asList(mapper.readValue(dirPath.resolve(path_string).toFile(), clazz)));
    }

    
    //method for writing to file
    private static <T> void writeFile(String path, ArrayList<T> data) throws IOException {
        File file = new File(dirPath.resolve(path).toString());
        FileWriter fileWriter = new FileWriter(file, false);
        SequenceWriter writer = mapper.writer().writeValues(fileWriter);

        writer.write(data);
        writer.close();
    }

    //returns all users
    public ArrayList<User> getAllUsers() throws JsonParseException, JsonMappingException, IOException {
        return readFileAsArray(userPath, User[].class);
    }

    //returns a user with the email in the argument, if there are no such user the method returns null
    private User getUser(String email) throws JsonParseException, JsonMappingException, IOException {
        ArrayList<User> users = getAllUsers();

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }

        return null;
     }

    
     //checks if password is correct, returns false if not
    public boolean checkPassword(String email, String pwd) throws IOException {
        ArrayList<User> users = getAllUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user.getPassword().equals(pwd);
            }
        }
        return false;        
     }

     //returns all houses
     public ArrayList<House> getAllHouses() throws JsonParseException, JsonMappingException, IOException {
         return readFileAsArray(housePath, House[].class);
     }
     
     //adds a user and returns it, if a user with the same email exists, it throws an exception
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

     //remove user 
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

     //adds house to a user
     public House addHouse(String location, String userEmail) throws JsonParseException, JsonMappingException, IOException, IllegalArgumentException {
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

     //sets house available, if the house is already unavailable and the status is false it throws an exception
     public House setAvailableHouse(String location, boolean status, String userEmail) throws IOException{
        ArrayList<House> houses = getAllHouses();
        for (House h: houses){
            if (h.getLocation().equals(location)){
                if (h.isAvailable() == false && h.isAvailable() == status){
                    throw new IllegalArgumentException("House is already unavailable");
                }
            }
        }
       
        House house = removeHouse(location, userEmail);
        
        house.setAvailable(status);

        ArrayList<House> houses2 = getAllHouses();

        houses2.add(house);
        writeFile(housePath, houses2);
        return house;
     }

    //removes a house connected to the email
    //if the house does not exist or it is nor connected to the email it throws an exception
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
