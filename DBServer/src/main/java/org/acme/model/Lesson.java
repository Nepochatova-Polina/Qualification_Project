package org.acme.model;

import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Lesson {
  private Long id;
  private String description;
  private Set<Materials> materials;

  public Lesson(Long id, String description) {
    this.id = id;
    this.description = description;
  }


  public static Lesson fromRowSet(RowSet<Row> rows){
    Lesson lesson = new Lesson();
    Set<Materials> materials = new HashSet<>();
    Row row1 = rows.iterator().next();

    lesson.setId(row1.getLong("id"));
    lesson.setDescription(row1.getString("description"));

    for (Row row : rows) {
      materials.add(new Materials(row.getLong(3), row.getBuffer("material").getBytes()));

    }
    lesson.setMaterials(materials);
    return lesson;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Lesson lesson = (Lesson) o;
    return Objects.equals(id, lesson.id) && Objects.equals(description, lesson.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description);
  }

}
