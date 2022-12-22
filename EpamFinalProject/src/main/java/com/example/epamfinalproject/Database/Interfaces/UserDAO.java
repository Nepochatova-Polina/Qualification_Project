package com.example.epamfinalproject.Database.Interfaces;

import com.example.epamfinalproject.Entities.User;

import java.io.InputStream;
import java.util.List;

public interface UserDAO {
    void registerUser(User user);

    void deleteUserByID(long id);

    void updateUserByID(User user, long id);

    void updateUserPassport(long id, InputStream image, long length);

    User getUserByName(String name, String password);

    User getUserByUsername(String name);

    User getUserByID(long id);

    List<User> getClientUsers();

    List<User> getAdministratorUsers();

    List<User> getAllUsers();
}
