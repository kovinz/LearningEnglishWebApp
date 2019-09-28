package ru.kovin.web_app_for_learning_english.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kovin.web_app_for_learning_english.entities.identifiers.DictionaryId;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(DictionaryId.class)
public class Dictionary {
  @ManyToOne
  @JoinColumn(name = "user_id")
  @Id
  private User user;
  @ManyToOne
  @JoinColumn(name = "eng_word_id")
  @Id
  private EngWord engWord;
  private String imgPath;
  private int progress;
}
