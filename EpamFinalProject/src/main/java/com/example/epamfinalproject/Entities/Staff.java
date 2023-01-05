package com.example.epamfinalproject.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 *  Creates a new instance of {@code Staff}.
 */
public class Staff {
    private long id;
    private String firstName;
    private String lastName;
    private long ship_id;

    /**
     * Constructor - creating new object with specific values
     *
     * @param firstName  First name of staff
     * @param lastName   Last name of staff
     * @param ship_id    ID of the ship on which the staff is listed
     */
    public Staff(String firstName, String lastName, long ship_id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ship_id = ship_id;
    }
}
