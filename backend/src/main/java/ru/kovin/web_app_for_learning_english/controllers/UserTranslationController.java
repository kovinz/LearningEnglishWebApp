package ru.kovin.web_app_for_learning_english.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kovin.web_app_for_learning_english.entities.EngWord;
import ru.kovin.web_app_for_learning_english.entities.User;
import ru.kovin.web_app_for_learning_english.entities.UserTranslation;
import ru.kovin.web_app_for_learning_english.repositories.EngWordRepository;
import ru.kovin.web_app_for_learning_english.repositories.UserRepository;
import ru.kovin.web_app_for_learning_english.repositories.UserTranslationRepository;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserTranslationController {
  private final Logger log = LoggerFactory.getLogger(EngWordController.class);

  private final UserTranslationRepository userTranslationRepository;
  private final EngWordRepository engWordRepository;
  private final UserRepository userRepository;

  public UserTranslationController(UserTranslationRepository userTranslationRepository,
                                   EngWordRepository engWordRepository,
                                   UserRepository userRepository) {
    this.userTranslationRepository = userTranslationRepository;
    this.engWordRepository = engWordRepository;
    this.userRepository = userRepository;
  }

  @GetMapping("/user_translations")
  public List<UserTranslation> all() {
    return userTranslationRepository.findAll();
  }

  @GetMapping("/user_translations/{user_id}/{eng_word_id}")
  public ResponseEntity<?> getUserTranslationsOfOneWord(@PathVariable Long user_id, @PathVariable Long eng_word_id) {
    Optional<User> user = userRepository.findById(user_id);
    Optional<EngWord> engWord = engWordRepository.findById(eng_word_id);
    if (user.isEmpty() || engWord.isEmpty()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    List<UserTranslation> userDictionaries =
            userTranslationRepository.findUserTranslationsByUserAndEngWord(user.get(), engWord.get());
    return ResponseEntity.ok().body(userDictionaries);
  }


  @PostMapping("/user_translations")
  ResponseEntity<UserTranslation> createDictionary(@Valid @RequestBody UserTranslation userTranslation)
          throws URISyntaxException {
    log.info("Request to create user_translation: {}", userTranslation);

    UserTranslation result = userTranslationRepository.save(userTranslation);
    return ResponseEntity.created(new URI("/api/user_translations/" + result.getUser().getUserId()
            + "/" + result.getEngWord().getWord() + "/" + result.getRuWord().getWord()))
            .body(result);
  }
}
