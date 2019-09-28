package ru.kovin.web_app_for_learning_english.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kovin.web_app_for_learning_english.entities.Dictionary;
import ru.kovin.web_app_for_learning_english.entities.User;
import ru.kovin.web_app_for_learning_english.entities.identifiers.DictionaryId;

import java.util.List;

public interface DictionaryRepository  extends JpaRepository<Dictionary, DictionaryId> {
  List<Dictionary> findDictionariesByUser(User user);
}
