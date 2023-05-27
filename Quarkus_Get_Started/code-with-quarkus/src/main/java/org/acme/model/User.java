package org.acme.model;

import io.vertx.mutiny.sqlclient.Row;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import io.vertx.mutiny.sqlclient.RowSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.acme.model.enums.Level;
import org.acme.model.enums.Role;
import org.acme.model.enums.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private Long id;
  private String firstname;
  private String lastname;
  private String email;
  private Role role;
  private List<Course> courses;

  public User(Long id, String firstname, String lastname, String email) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
  }


  public static User from(RowSet<Row> rows) {
    User user = new User();
    List<Course> courses = new ArrayList<>();
    for (Row row : rows) {
      Course course = new Course();

      user.setId(row.getLong("id"));
      user.setFirstname(row.getString("firstname"));
      user.setLastname(row.getString("lastname"));
      user.setEmail(row.getString("email"));
      user.setRole(Role.fromString(row.getString("role")));

      course.setName(row.getString("name"));
      course.setAnnotation(row.getString("annotation"));
      course.setStatus(Status.fromString(row.getString("status")));
      course.setLevel(Level.fromString(row.getString("level")));
      courses.add(course);
    }
    user.setCourses(courses);
    return user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id) && Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname) && Objects.equals(email, user.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, email);
  }
}
