package core;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UserTest {
    private User user;

    @Test
    public void testConstructor() {
        user = new User("mail@mail.com", "123");
        assertEquals("mail@mail.com", user.getEmail());
        assertEquals("123", user.getPassword());

        assertThrows(IllegalArgumentException.class, () -> {New User (null, "");});
        assertThrows(IllegalArgumentException.class, () -> {New User (null, "123");});
        assertThrows(IllegalArgumentException.class, () -> {New User("mail@mail.com", null);});
        
    }

    @Test
    public void testInvalidEmail(){

        assertThrows(IllegalArgumentException.class, () -> {New User("email", "123");});
        assertThrows(IllegalArgumentException.class, () -> {New User("e@e", "123");});
        assertThrows(IllegalArgumentException.class, () -> {New User("e.no", "123");});
        assertThrows(IllegalArgumentException.class, () -> {New User("æøå@gmail.com", "123");});

    }
}
