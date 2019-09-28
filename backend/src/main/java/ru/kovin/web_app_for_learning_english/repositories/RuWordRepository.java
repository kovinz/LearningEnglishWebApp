package ru.kovin.web_app_for_learning_english.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kovin.web_app_for_learning_english.entities.RuWord;

public interface RuWordRepository extends JpaRepository<RuWord, Long> {
}
