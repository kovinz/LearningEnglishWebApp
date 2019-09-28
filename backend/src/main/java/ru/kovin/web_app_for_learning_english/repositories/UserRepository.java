package ru.kovin.web_app_for_learning_english.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kovin.web_app_for_learning_english.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
