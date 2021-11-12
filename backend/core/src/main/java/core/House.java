package core;

// Et hus skal inneholde adresse, beskrivelse og tilhørende bruker

public class House {
    private final String location;
    private User user;
    private boolean isAvailable = true;

    public House() {
        user = new User();
        location = "";
    }

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
