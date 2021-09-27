package core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    public Main(String email, String password, String confirmPassword) {
        registerUser(email, password, confirmPassword);
        logInUser(email, password);
    }

    // kalles ved innlogging
    public Main(String email, String password) {
        logInUser(email, password);
    }

    private void registerUser(String email, String password, String confirmPassword) {
        if (!password.equals(confirmPassword))
            throw new IllegalArgumentException("password must match confirmation");

        users.add(new User(email, password));
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

    public List<House> getAvailableHousing() {
        return getHousesWithFilter(house -> house.isAvailable());
    }

    public List<House> getHousing() {
        return new ArrayList<House>(houses);
    }

    public void HostNewHouse(String location) {
        // må sjekke om lokasjonen er unik
        if (getHousesWithFilter(house -> house.getLocation().equals(location)).size() > 0)
            throw new IllegalArgumentException("this house is already registered");

        if (currentUser == null)
            throw new IllegalStateException("you must login before hosting a house");

        House house = new House(location, currentUser);
        houses.add(house);
    }

    public static void main(String[] args) {
        Main program = new Main("erl@mail.com", "123", "123");
        program.HostNewHouse("adresse 72b");
        System.out.println(program.getAvailableHousing());
    }
}