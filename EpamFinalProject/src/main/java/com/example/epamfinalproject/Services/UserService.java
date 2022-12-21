package com.example.epamfinalproject.Services;

import com.example.epamfinalproject.Database.Implementations.User_Implementation;
import com.example.epamfinalproject.Database.Interfaces.UserDAO;
import com.example.epamfinalproject.Entities.User;

import java.util.List;

public class UserService {
    public static void registerUser(User user) {
        UserDAO userDAO = new User_Implementation();
        userDAO.registerUser(user);
    }

    public static void updateUserByID(User user, long id) {
        UserDAO userDAO = new User_Implementation();
        userDAO.updateUserByID(user, id);
    }

    public static void deleteUserByID(long id) {
        UserDAO userDAO = new User_Implementation();
        userDAO.deleteUserByID(id);
    }

    public static User getUserByID(long id) {
        UserDAO userDAO = new User_Implementation();
        return userDAO.getUserByID(id);
    }

    public static User getUserByName(String name, String password) {
        UserDAO userDAO = new User_Implementation();
        return userDAO.getUserByName(name, password);
    }

    public static List<User> getAllUsers() {
        UserDAO userDAO = new User_Implementation();
        return userDAO.getAllUsers();
    }

    public static List<User> getPassengerUsers() {
        UserDAO userDAO = new User_Implementation();
        return userDAO.getClientUsers();
    }

    public static List<User> getAdministratorUsers() {
        UserDAO userDAO = new User_Implementation();
        return userDAO.getAdministratorUsers();
    }

}
