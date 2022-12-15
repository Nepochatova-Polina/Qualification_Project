package com.example.epamfinalproject.Entities;

import com.example.epamfinalproject.Entities.Enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private UserRole role;
    private byte[] passport;

    private User(UserBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.login = builder.login;
        this.password = builder.password;
        this.role = builder.role;
        this.passport = builder.passport;
    }

    public static class UserBuilder {
        private long id;
        private String firstName;
        private String lastName;
        private String login;
        private String password;
        private UserRole role;
        private byte[] passport;

        public UserBuilder() {}

        public UserBuilder id(long id) {
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

        public UserBuilder passport(byte[] passport) {
            this.passport = passport;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}


