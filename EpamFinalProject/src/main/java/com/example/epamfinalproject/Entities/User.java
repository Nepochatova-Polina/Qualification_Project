package com.example.epamfinalproject.Entities;

import com.example.epamfinalproject.Entities.Enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private UserRole role;

    private User(UserBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.login = builder.login;
        this.password = builder.password;
        this.role = builder.role;
    }

    public static class UserBuilder {
        private int id;
        private String firstName;
        private String lastName;
        private String login;
        private String password;
        private UserRole role;

        public UserBuilder() {}

        public UserBuilder id(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder login(String login) {
            this.login = login;
            return this;
        }
        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder role(UserRole role) {
            this.role = role;
            return this;
        }

        public User build() {
            //            validateUserObject(user);
            return new User(this);
        }

//        private void validateUserObject(User user) {
//            Do some basic validations to check
            //if user object does not break any assumption of system
//        }
    }
}


