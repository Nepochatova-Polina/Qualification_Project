package org.acme.model;

import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.acme.model.Test.Test;
import org.acme.model.enums.Level;
import org.acme.model.enums.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
  private Long id;
  private Long professorID;
  private String name;
  private String annotation;
  private Status status;
  private Level level;
  private Set<Lesson> lessons;
  private Set<Test> tests;
  private Set<User> students;

  public Course(
      Long id, Long professorID, String name, String annotation, Status status, Level level) {
    this.id = id;
    this.professorID = professorID;
    this.name = name;
    this.annotation = annotation;
    this.status = status;
    this.level = level;
  }

  public static Course fromSet(RowSet<Row> rows) {
    Set<Lesson> lessons = new LinkedHashSet<>();
    Set<Test> tests = new LinkedHashSet<>();
    Set<User> students = new LinkedHashSet<>();
    Course course = new Course();

    Row row1 = rows.iterator().next();

    course.setId(row1.getLong("id"));
    course.setName(row1.getString("name"));
    course.setAnnotation(row1.getString("annotation"));
    course.setStatus(Status.fromString(row1.getString("status")));
    course.setLevel(Level.fromString(row1.getString("level")));

    for (Row row : rows) {
      lessons.add(new Lesson(row.getLong(11), row.getString("description")));
      tests.add(new Test(row.getLong(26), row.getString("test_name")));
      students.add(
          new User(
              row.getLong(15),
              row.getString("firstname"),
              row.getString("lastname"),
              row.getString("email")));
    }
    course.setLessons(lessons);
    course.setTests(tests);
    course.setStudents(students);
    return course;
  }

  public static Course shortDescription(Row row) {
    return new Course(
        row.getLong("id"),
        row.getLong("professor_id"),
        row.getString("name"),
        row.getString("annotation"),
        Status.fromString(row.getString("status")),
        Level.fromString(row.getString("level")));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Course course = (Course) o;
    return Objects.equals(professorID, course.professorID)
        && Objects.equals(name, course.name)
        && Objects.equals(annotation, course.annotation)
        && status == course.status
        && level == course.level;
  }

  @Override
  public int hashCode() {
    return Objects.hash(professorID, name, annotation, status, level);
  }
}
