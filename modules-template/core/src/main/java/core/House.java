package core;

public class House {
    private final String location;
    private User user;
    private boolean isAvailable = true;

    public House() {
        this.location = "";
        this.user = new User();
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
