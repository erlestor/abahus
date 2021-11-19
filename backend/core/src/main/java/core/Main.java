package core;

import jsonworker.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

// Main klassen inneholder listen over boliger og brukere og består av hovedfunksjonaliteten
// Det er denne kontrolleren skal ha tilgang til
// Vurder å lag flere hjelpeklasser til denne
// Funksjoner: registrer bruker, logg inn bruker, få liste over tilgjengelige hus, legge ut hus
// Antar her at lokasjon er unik til hvert hus

public class Main {
    // foreløpig starter users/houses som tomme hver gang, men de vil bli hentet fra lagring etterhvert
    private Collection<User> users = new ArrayList<>();
    private Collection<House> houses = new ArrayList<>();
    private User currentUser;

    // kalles ved registrering
    public Main(String email, String password, String confirmPassword) throws JsonParseException, JsonMappingException, IOException {
        loadJson();
        registerUser(email, password, confirmPassword);
        logInUser(email, password);
    }

    // kalles ved innlogging
    public Main(String email, String password) throws JsonParseException, JsonMappingException, IOException {
        loadJson();
        logInUser(email, password);
    }

    private void loadJson() throws JsonParseException, JsonMappingException, IOException {
        houses = Jsonworker.getAllHouses();
        users = Jsonworker.getAllUsers();
    }

    private void registerUser(String email, String password, String confirmPassword) throws IllegalArgumentException, IOException {
        if (!password.equals(confirmPassword))
            throw new IllegalArgumentException("password must match confirmation");

        if (users.stream().anyMatch(user -> user.getEmail().equals(email)))
            throw new IllegalStateException("There already exists a user with the same email");

        Jsonworker.addUser(email, password);
        loadJson();
    } 

    private void logInUser(String email, String password) {
        User currentUser = users.stream()
                                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                                .findFirst().orElse(null);
        
        if (currentUser == null)
            throw new IllegalStateException("no user found with that combination");

        this.currentUser = currentUser;
    }

    private List<House> getHousesWithFilter(Predicate<House> pr) {
        return new ArrayList<House>(houses.stream()
                                            .filter(pr)
                                            .collect(Collectors.toList()));
    }

    // returns list of houses that logged in user owns
    public List<House> getMyHouses() {
        return getHousesWithFilter(h -> h.getUser().getEmail().equals(currentUser.getEmail()));
    }

    public List<House> getAvailableHousing() {
        return getHousesWithFilter(house -> house.isAvailable() && !house.getUser().getEmail().equals(currentUser.getEmail()));
    }

    public List<House> getHousing() {
        return new ArrayList<House>(houses);
    }

    public void hostNewHouse(String location) throws JsonParseException, JsonMappingException, IllegalArgumentException, IOException {
        // må sjekke om lokasjonen er unik
        if (getHousesWithFilter(house -> house.getLocation().equals(location)).size() > 0)
            throw new IllegalArgumentException("this house is already registered");

        if (currentUser == null)
            throw new IllegalStateException("you must login before hosting a house");

        Jsonworker.addHouse(location, currentUser.getEmail());
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

    public void removeHouse(House house) throws IOException {
        Jsonworker.removeHouse(house.getLocation(), house.getUser().getEmail());
        loadJson();
    }

    public void removeUser(User user) throws IOException {
        Jsonworker.removeUser(user.getEmail());
        loadJson();

    }

}
