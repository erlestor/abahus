package core;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UserTest {
    private User user;

    //Tester konstruktøren
    @Test
    public void testConstructor() {
        user = new User("mail@mail.com", "123");
        assertEquals("mail@mail.com", user.getEmail());
        assertEquals("123", user.getPassword());

        assertThrows(IllegalArgumentException.class, () -> {new User (null, "");});
        assertThrows(IllegalArgumentException.class, () -> {new User (null, "123");});
        assertThrows(IllegalArgumentException.class, () -> {new User("mail@mail.com", null);});
        
    }

    //Tester at eposten må være skrevet riktig
    @Test
    public void testInvalidEmail(){
        assertThrows(IllegalArgumentException.class, () -> {new User("email", "123");});
        assertThrows(IllegalArgumentException.class, () -> {new User("e@e", "123");});
        assertThrows(IllegalArgumentException.class, () -> {new User("e.no", "123");});
        assertThrows(IllegalArgumentException.class, () -> {new User("æøå@gmail.com", "123");});

    }
}
