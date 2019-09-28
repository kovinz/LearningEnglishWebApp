package ru.kovin.web_app_for_learning_english.entities.identifiers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryId implements Serializable{
  private Long user;
  private Long engWord;
}
