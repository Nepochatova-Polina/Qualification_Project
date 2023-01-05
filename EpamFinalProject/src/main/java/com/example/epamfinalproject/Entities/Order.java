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
    private int numberOfSeats;
    private int price;
    private Status status = Status.PENDING;

    /**
     * Constructor - creating new object with specific values
     *
     * @param cruise        Cruise on which seats are purchased
     * @param user          Client, who bought cruise
     * @param price         Total price
     * @param numberOfSeats Number of seats purchased by the client
     */
    public Order(Cruise cruise, User user, int numberOfSeats, int price) {
        this.cruise = cruise;
        this.user = user;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
    }
}
