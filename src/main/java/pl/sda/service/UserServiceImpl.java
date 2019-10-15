package pl.sda.service;

import pl.sda.dao.UserDao;
import pl.sda.dao.UserDaoImpl;
import pl.sda.model.User;
import pl.sda.util.PasswordUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean isUserValid(String login, String password) {
        User user = userDao.getUser(login);
        return user!=null && login.equals(user.getUsername()) && PasswordUtil.checkPassword(password, user.getPassword())&& userDao.isUserActivated(login);
    }

    @Override
    public String addUser(String login, String password) {
        System.out.println("Data in "+ UserServiceImpl.class+ " addUser method with login: "+login + ", password: "+ password);
        String hashPassword = PasswordUtil.hashPassword(password);
        System.out.println("Hash password "+password + " to value: "+hashPassword);
        String token = userDao.addUser(login, hashPassword);
        return token;
    }

    @Override
    public boolean activateUser(String token) {
        return userDao.activateUser(token);
    }

    @Override
    public String getUsernameByToken(String token) {
        return userDao.getUsernameByToken(token);
    }

    @Override
    public boolean isUserRegistered(String username) {
        return userDao.isUserRegistered(username);
    }

    @Override
    public boolean isUserActivated(String username) {
        return userDao.isUserActivated(username);
    }


}
