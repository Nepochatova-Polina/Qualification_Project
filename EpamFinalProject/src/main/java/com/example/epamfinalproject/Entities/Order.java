package com.example.epamfinalproject.Entities;

import com.example.epamfinalproject.Entities.Enums.Status;
import java.util.Objects;
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

  /**
   * Constructor - creating new object with specific values
   *
   * @param cruise Cruise on which seats are purchased
   * @param user Client, who bought cruise
   */
  public Order(Cruise cruise, User user) {
    this.cruise = cruise;
    this.user = user;
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
