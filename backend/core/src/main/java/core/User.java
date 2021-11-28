package core;
import java.util.UUID;

//Cannot change user so we only have getters
public class User {
    private final String email;
    private final String password;
    private final String userId  = UUID.randomUUID().toString();

    //constructor without parameters
    public User() {
        email = "";
        password = "";
    }

    //constructor with parameters
    public User(String email, String password) {
        if (email == null || password == null || email.isBlank() || password.isBlank())
            throw new IllegalArgumentException("Email and password must not be null or empty");

        if (!isEmailValid(email))
            throw new IllegalArgumentException("Email is invalid");

        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    //Email have to be valid, using regex to make sure
    private boolean isEmailValid(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
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
