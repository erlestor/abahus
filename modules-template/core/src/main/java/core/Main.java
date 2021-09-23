package core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// Main klassen inneholder listen over boliger og brukere
// Det er denne kontrolleren skal ha tilgang til
// Vurder å lag flere hjelpeklasser til denne
// registrer bruker, log inn bruker, få liste over tilgjengelige hus, legge ut hus

public class Main {
    // foreløpig starter disse som tomme hver gang, men de vil bli hentet fra lagring etterhvert
    private Collection<User> users = new ArrayList<>();
    private Collection<House> houses = new ArrayList<>();
    private User currentUser;

    public void registerUser(String email, String password) {
        users.add(new User(email, password));
    } 

    public void logInUser(String email, String password) {
        User currentUser = users.stream()
                                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                                .findFirst().orElse(null);
        
        if (currentUser == null)
            throw new IllegalStateException("no user found with that combination");

        this.currentUser = currentUser;
    }

    public List<House> getAvailableHousing() {
        return new ArrayList<House>(houses.stream()
                                            .filter(house -> house.isAvailable())
                                            .collect(Collectors.toList()));
    }

    public void HostNewHouse(String location) {
        House house = new House(location, currentUser);
        houses.add(house);
    }
}
