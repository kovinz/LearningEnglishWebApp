package ru.kovin.web_app_for_learning_english.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;
  private String nickname;
  private String password;
  private String firstName;
  private String lastName;
  private String email;
  private String gender;
  private Date dateOfBirth;
  private String country;
  private String city;
  private String imgPath;
  private int points;
  private int level;
  private boolean premium;
}
