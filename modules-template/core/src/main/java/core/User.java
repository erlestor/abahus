package core;
import java.util.UUID;

// En user skal lagre email, passord og en tilfeldig valgt ID (bruker library til sistnevnte)
// I utgangspunktet skal man ikke kunne endre på en bruker. Så trenger bare gettere

public class User {
    private final String email;
    private final String password;
    private final String userId  = UUID.randomUUID().toString();;
   
    public User(String email, String password) {
        if (email == null || password == null || email.isBlank() || password.isBlank())
            throw new IllegalArgumentException("email and password must not be null or empty");

        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return this.email;
    }


    public String getPassword() {
        return this.password;
    }


    @Override
    public String toString() {
        return "{" +
            " email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", userId='" + userId + "'" +
            "}";
    }

}
