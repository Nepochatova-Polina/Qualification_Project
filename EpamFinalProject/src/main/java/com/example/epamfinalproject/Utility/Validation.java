package com.example.epamfinalproject.Utility;

import com.example.epamfinalproject.Entities.User;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean validateUserFields(User user){
        return  Pattern.compile(Regex.USER_LOGIN).matcher(user.getLogin()).find() &&
                Pattern.compile(Regex.USER_FIRSTNAME).matcher(user.getFirstName()).find() &&
                Pattern.compile(Regex.USER_LASTNAME).matcher(user.getLastName()).find();
    }

}
