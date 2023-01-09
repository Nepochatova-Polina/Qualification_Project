package com.example.epamfinalproject.Utility;

/**
 * The class stores patterns for validating fields that the user enters
 */
public class Regex {

    //User SignUpCommand regex
    public static final String USER_LOGIN = "^[A-Za-z0-9_]{4,}$";
    public static final String USER_FIRSTNAME = "^[A-ZЄІЇА-Я][a-zа-яёєії]+$";
    public static final String USER_LASTNAME = "^[A-ZЄІЇА-Я][a-zа-яёєії]+$";
}
