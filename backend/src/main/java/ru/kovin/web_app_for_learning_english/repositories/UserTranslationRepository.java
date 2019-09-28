package ru.kovin.web_app_for_learning_english.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kovin.web_app_for_learning_english.entities.EngWord;
import ru.kovin.web_app_for_learning_english.entities.User;
import ru.kovin.web_app_for_learning_english.entities.UserTranslation;
import ru.kovin.web_app_for_learning_english.entities.identifiers.UserTranslationId;

import java.util.List;

public interface UserTranslationRepository extends JpaRepository<UserTranslation, UserTranslationId> {
  List<UserTranslation> findUserTranslationsByUserAndEngWord(User user, EngWord engWord);
}
