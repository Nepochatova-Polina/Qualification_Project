package org.acme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.acme.model.Test.Test;
import org.acme.model.enums.Level;
import org.acme.model.enums.Status;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
  private Long id;
  private Long professorID;
  private String name;
  private String annotation;
  private String code;
  private Status status = Status.PENDING;
  private Level level;
  private Set<Lesson> lessons;
  private Set<Test> tests;
  private Set<User> students;

  public Course(Long id, Long professorID, String name, String annotation, String code, Status status, Level level) {
    this.id = id;
    this.professorID = professorID;
    this.name = name;
    this.annotation = annotation;
    this.code = code;
    this.status = status;
    this.level = level;
  }
}
