package ru.kovin.web_app_for_learning_english.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EngWord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long engWordId;
  private String Word;
}
