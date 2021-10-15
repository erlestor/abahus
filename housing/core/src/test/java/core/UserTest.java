package core;
import jsonworker.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UserTest {
    private User user;

    @Test
    public void testConstructor() {
        user = new User("mail@mail.com", "123");
        assertEquals("mail@mail.com", user.getEmail());
        assertEquals("123", user.getPassword());
    }
}
