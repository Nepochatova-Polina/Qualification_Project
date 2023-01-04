package com.example.epamfinalproject.Database.Shaper;

import com.example.epamfinalproject.Database.FieldKey;
import com.example.epamfinalproject.Entities.Enums.UserRole;
import com.example.epamfinalproject.Entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserShaper implements DataShaper<User> {
    @Override
    public User shapeData(ResultSet resultSet) throws SQLException {

        return new User.UserBuilder()
                .id(resultSet.getLong(FieldKey.ENTITY_ID))
                .firstName(resultSet.getString(FieldKey.FIRST_NAME))
                .lastName(resultSet.getString(FieldKey.LAST_NAME))
                .login(resultSet.getString(FieldKey.LOGIN))
                .password(resultSet.getString(FieldKey.PASSWORD))
                .role(UserRole.fromString(resultSet.getString(FieldKey.ROLE)))
                .build();
    }

    @Override
    public List<User> shapeDataToList(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        User user;
        if(resultSet.next()){
            user = new User.UserBuilder()
                    .id(resultSet.getLong(FieldKey.ENTITY_ID))
                    .firstName(resultSet.getString(FieldKey.FIRST_NAME))
                    .lastName(resultSet.getString(FieldKey.LAST_NAME))
                    .login(resultSet.getString(FieldKey.LOGIN))
                    .password(resultSet.getString(FieldKey.PASSWORD))
                    .role(UserRole.fromString(resultSet.getString(FieldKey.ROLE)))
                    .build();
            userList.add(user);
        }
        return userList;
    }
}
