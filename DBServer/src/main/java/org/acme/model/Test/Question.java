package org.acme.model.Test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Question {
  private Long id;
  private String question;
  private List<Answer> answers;

  public Question(Long id, String question) {
    this.id = id;
    this.question = question;
  }
}
