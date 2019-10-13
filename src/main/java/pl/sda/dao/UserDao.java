package pl.sda.dao;

import pl.sda.model.User;

public interface UserDao {

    User getUser(String username);
    String addUser(String username, String password);

}
