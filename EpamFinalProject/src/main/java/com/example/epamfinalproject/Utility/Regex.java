package com.example.epamfinalproject.Utility;

/** The class stores patterns for validating fields that the user enters */
public class Regex {

  private Regex() {}

  // User SignUpCommand Regex
  public static final String USER_LOGIN = "^[A-Za-z0-9_]{4,}+$";
  public static final String USER_FIRSTNAME = "^[A-Za-z0-9-].{1,}+$";
  public static final String USER_LASTNAME = "^[A-Za-z0-9-].{1,}+$";

  // Ship CreateCommand Regex
  public static final String SHIP_NAME = "^[A-ZЄІЇА-Я]*[a-zа-яёєії\\s'].{1,}+$";

  // Route CreateCommand Regex
  public static final String ROUTE_DEPARTURE = "^[A-ZЄІЇА-Я]*[a-zа-яёєії\\s'].{1,}+$";
  public static final String ROUTE_DESTINATION = "^[A-ZЄІЇА-Я]*[a-zа-яёєії\\s'].{1,}+$";

  // Cruise CreateCommand Regex
  public static final String CRUISE_NAME = "^[A-ZЄІЇА-Я]*[a-zа-яёєії\\s'].{1,}+$";
}
