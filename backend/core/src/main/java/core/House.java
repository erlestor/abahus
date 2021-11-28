package core;

// A house contains an address, user and availability

public class House {
    private final String location;
    private User user;
    private boolean isAvailable = true;

    //Constructor without parameters
    public House() {
        user = new User();
        location = "";
    }

    //Constructor with two parameters
    public House(String location, User user) {
        if (location == null || user == null)
            throw new NullPointerException("input cannot be null");
            
        if (location.isBlank())
            throw new IllegalArgumentException("Location cannot be blank");

        this.location = location;
        this.user = user;
    }

    public String getLocation() {
        return this.location;
    }

    public User getUser() {
        return this.user;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    //A setter for availability that can only be used if the argument is different than the state
    public void setAvailable(boolean isAvailable) {
        if (isAvailable == false && isAvailable() == false){
            throw new IllegalArgumentException("House is already in use");
        }
        this.isAvailable = isAvailable;
    }
    

    @Override
    public String toString() {
        return "{" +
            " location='" + getLocation() + "'" +
            ", user='" + getUser() + "'" +
            ", isAvailable='" + isAvailable() + "'" +
            "}";
    }

}
