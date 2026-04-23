package model;

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
