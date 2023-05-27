package org.acme.model.Test;

import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.acme.model.Lesson;
import org.acme.model.Materials;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Test {
  private Long id;
  private String name;
  private Set<Question> questions;

  public Test(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public static Test fromRowSet(RowSet<Row> rows) {
    Test test = new Test();
    Set<Question> questionSet = new HashSet<>();
    Row row1 = rows.iterator().next();

    test.setId(row1.getLong("id"));
    test.setName(row1.getString("name"));

    for (Row row : rows) {
      questionSet.add(new Question());

    }
    test.setQuestions(questionSet);
    return new Test();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Test test = (Test) o;
    return Objects.equals(id, test.id) && Objects.equals(name, test.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
