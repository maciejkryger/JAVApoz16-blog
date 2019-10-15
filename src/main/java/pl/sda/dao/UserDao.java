package pl.sda.dao;

import pl.sda.model.User;

public interface UserDao {

    User getUser(String username);
    String addUser(String username, String password);
    boolean activateUser(String token);
    String getUsernameByToken(String token);
    Long getUserIdByToken(String token);
    boolean isUserRegistered(String username);
    boolean isUserActivated(String username);
}
