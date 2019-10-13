package pl.sda.service;

public interface UserService {

    boolean isUserValid(String login, String password);

    String addUser(String login, String password);

}
