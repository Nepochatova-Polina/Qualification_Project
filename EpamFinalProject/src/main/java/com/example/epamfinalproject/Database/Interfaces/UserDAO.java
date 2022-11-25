package com.example.epamfinalproject.Database.Interfaces;

import com.example.epamfinalproject.Entities.User;

import java.util.List;

public interface UserDAO {
    void registerUser(User user);

    void deleteUserByID(User user);

    void updateUserByID(User user, long id);

    User getUserByName(String name, String password);

    User getUserByID(long id);

    List<User> getPassengerUsers();

    List<User> getAdministratorUsers();

    List<User> getAllUsers();
}
