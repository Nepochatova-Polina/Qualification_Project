package com.example.epamfinalproject.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    private long id;
    private String firstName;
    private String lastName;
    private long ship_id;

    public Staff(String firstName, String lastName, long ship_id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ship_id = ship_id;
    }
}
