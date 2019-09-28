package ru.kovin.web_app_for_learning_english.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kovin.web_app_for_learning_english.entities.identifiers.UserTranslationId;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(UserTranslationId.class)
public class UserTranslation {
  @ManyToOne
  @JoinColumn(name = "user_id")
  @Id
  private User user;
  @ManyToOne
  @JoinColumn(name = "eng_word_id")
  @Id
  private EngWord engWord;
  @ManyToOne
  @JoinColumn(name = "ru_word_id")
  @Id
  private RuWord ruWord;
}
