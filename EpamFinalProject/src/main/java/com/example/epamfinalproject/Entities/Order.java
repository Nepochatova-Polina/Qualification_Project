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
    private Cruise cruise;
    private User user;
    private Status status = Status.PENDING;


    public Order(Cruise cruise, User user) {
        this.cruise = cruise;
        this.user = user;
    }
}
