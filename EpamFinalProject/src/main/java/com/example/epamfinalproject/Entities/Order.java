package com.example.epamfinalproject.Entities;

import com.example.epamfinalproject.Entities.Enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private long id;
    private long shipID;
    private long userID;
    private Status status = Status.PENDING;

    public Order(long shipID, long userID) {
        this.shipID = shipID;
        this.userID = userID;
    }
}
