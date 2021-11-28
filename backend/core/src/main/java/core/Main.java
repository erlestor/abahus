package core;

import jsonworker.Jsonworker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

// The main class contains a list over users and houses and the core functionality
// This is the one the controller can access
// Assumes that houses cannot have same address

public class Main {
    private Collection<User> users = new ArrayList<>();
    private Collection<House> houses = new ArrayList<>();
    private Jsonworker worker = new Jsonworker();
    private User currentUser;

    //empty constructor
    public Main() throws JsonParseException, JsonMappingException, IOException {
        loadJson();
    }

    //constructor for registering
    public Main(String email, String password, String confirmPassword)
            throws JsonParseException, JsonMappingException, IOException {
        loadJson();
        registerUser(email, password, confirmPassword);
        logInUser(email, password);
    }

    //constructor for login 
    public Main(String email, String password) throws JsonParseException, JsonMappingException, IOException {
        loadJson();
        logInUser(email, password);
    }

    //method for loading list in main after every change
    private void loadJson() throws JsonParseException, JsonMappingException, IOException {
        houses = worker.getAllHouses();
        users = worker.getAllUsers();
    }

    //method for registering
    public void registerUser(String email, String password, String confirmPassword)
            throws IllegalArgumentException, IOException {
        if (this.currentUser != null) {
            throw new IllegalStateException("you are already logged in");
        }
        if (!password.equals(confirmPassword))
            throw new IllegalArgumentException("password must match confirmation");

        if (users.stream().anyMatch(user -> user.getEmail().equals(email)))
            throw new IllegalStateException("There already exists a user with the same email");

        worker.addUser(email, password);
        loadJson();
    }

    //method for login
    public void logInUser(String email, String password) {
        if (this.currentUser != null) {
            throw new IllegalStateException("you are already logged in");
        }
        User currentUser = users.stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password)).findFirst()
                .orElse(null);

        if (currentUser == null)
            throw new IllegalStateException("no user found with that combination");

        this.currentUser = currentUser;
    }

    //private help method for getting houses with a predicate
    private List<House> getHousesWithFilter(Predicate<House> pr) {
        return new ArrayList<House>(houses.stream().filter(pr).collect(Collectors.toList()));
    }

    // returns list of houses that logged in user owns
    public List<House> getMyHouses() {
        if (this.currentUser == null) {
            throw new IllegalStateException("you are not logged in");
        }
        return getHousesWithFilter(h -> h.getUser().getEmail().equals(currentUser.getEmail()));
    }

    //returns list of available houses
    public List<House> getAvailableHousing() {
        return getHousesWithFilter(
                house -> house.isAvailable() && !house.getUser().getEmail().equals(currentUser.getEmail()));
    }

    //returns all houses
    public List<House> getHousing() {
        return new ArrayList<House>(houses);
    }

    //hosts new house
    //location has to be unique and a user must be logged in
    public void hostNewHouse(String location)
            throws JsonParseException, JsonMappingException, IllegalArgumentException, IOException {
        if (getHousesWithFilter(house -> house.getLocation().equals(location)).size() > 0)
            throw new IllegalArgumentException("this house is already registered");

        if (currentUser == null)
            throw new IllegalStateException("you must login before hosting a house");

        worker.addHouse(location, currentUser.getEmail());
        loadJson();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String getHousesAsString(Collection<House> houses) {
        String s = "";

        for (House house : houses) {
            s += "Location: " + house.getLocation() + "\n" + "Owner: " + house.getUser().getEmail() + "\n\n";
        }

        return s.substring(0, s.length() - 2);
    }

    //removes a house
    //has to be your house and you need to be loged in
    public void removeHouse(House house) throws IOException {
        if (this.currentUser == null) {
            throw new IllegalStateException("you are not logged in");
        }

        if (!house.getUser().getEmail().equals(getCurrentUser().getEmail())) {
            throw new IllegalArgumentException("Its not your house");
        }

        worker.removeHouse(house.getLocation(), house.getUser().getEmail());
        loadJson();
    }

    //sets a house as available
    public void setAvailableHouse(String location, boolean status) throws IOException {
        worker.setAvailableHouse(location, status, this.currentUser.getEmail());
        loadJson();
    }

    //logout
    //have to be logged in
    public void logOut() {
        if (this.currentUser == null) {
            throw new IllegalStateException("you are not logged in");
        }

        this.currentUser = null;
    }

    //removes currentuser and all houses registered to that user
    public void removeUser() throws IOException {
        if (this.currentUser == null) {
            throw new IllegalStateException("you are not logged in");
        }
        List<House> myHouses = getMyHouses();

        for (House h : myHouses) {
            removeHouse(h);
        }
        worker.removeUser(this.currentUser.getEmail());
        loadJson();
        this.currentUser = null;

    }

}
