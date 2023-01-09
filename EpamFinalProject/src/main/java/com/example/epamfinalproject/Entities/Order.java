package com.example.epamfinalproject.Entities;

import com.example.epamfinalproject.Entities.Enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return cruise.getId() == order.cruise.getId() && user.getId() == order.user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(cruise, user);
    }
}
