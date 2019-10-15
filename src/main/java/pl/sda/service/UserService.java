package pl.sda.service;

public interface UserService {

    boolean isUserValid(String login, String password);
    String addUser(String login, String password);
    boolean activateUser(String token);
    String getUsernameByToken(String token);
    boolean isUserRegistered(String username);
    boolean isUserActivated(String username);
}
