package com.example.epamfinalproject.Services;

import com.example.epamfinalproject.Database.Implementations.User_Implementation;
import com.example.epamfinalproject.Database.Interfaces.UserDAO;
import com.example.epamfinalproject.Entities.User;

import java.io.InputStream;
import java.util.List;

public class UserService {
    public void registerUser(User user) {
        UserDAO userDAO = new User_Implementation();
        userDAO.registerUser(user);
    }

    public void updateUserByID(User user, long id) {
        UserDAO userDAO = new User_Implementation();
        userDAO.updateUserByID(user, id);
    }

    public void deleteUserByID(long id) {
        UserDAO userDAO = new User_Implementation();
        userDAO.deleteUserByID(id);
    }

    public User getUserByID(long id) {
        UserDAO userDAO = new User_Implementation();
        return userDAO.getUserByID(id);
    }

    public User getUserByName(String name, String password) {
        UserDAO userDAO = new User_Implementation();
        return userDAO.getUserByName(name, password);
    }

    public List<User> getAllUsers() {
        UserDAO userDAO = new User_Implementation();
        return userDAO.getAllUsers();
    }

    public List<User> getClientUsers() {
        UserDAO userDAO = new User_Implementation();
        return userDAO.getClientUsers();
    }

    public List<User> getAdministratorUsers() {
        UserDAO userDAO = new User_Implementation();
        return userDAO.getAdministratorUsers();
    }

    public User getUserByLogin(String login) {
        UserDAO userDAO = new User_Implementation();
        return userDAO.getUserByLogin(login);
    }
    public void updateUserPassport(long id, InputStream image, long length){
        UserDAO userDAO = new User_Implementation();
        userDAO.updateUserPassport(id, image, length);
    }

}
