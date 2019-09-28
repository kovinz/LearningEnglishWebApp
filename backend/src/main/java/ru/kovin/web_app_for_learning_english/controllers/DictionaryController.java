package ru.kovin.web_app_for_learning_english.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kovin.web_app_for_learning_english.entities.Dictionary;
import ru.kovin.web_app_for_learning_english.entities.User;
import ru.kovin.web_app_for_learning_english.repositories.DictionaryRepository;
import ru.kovin.web_app_for_learning_english.repositories.UserRepository;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/dictionaries",
        produces = "application/json")
@CrossOrigin(origins = "*")
public class DictionaryController {
  private final Logger log = LoggerFactory.getLogger(DictionaryController.class);

  private final DictionaryRepository dictionaryRepository;
  private final UserRepository userRepository;

  public DictionaryController(DictionaryRepository dictionaryRepository, UserRepository userRepository) {
    this.dictionaryRepository = dictionaryRepository;
    this.userRepository = userRepository;
  }

  @GetMapping
  public List<Dictionary> all() {
    return dictionaryRepository.findAll();
  }

  @GetMapping("/{user_id}")
  public ResponseEntity<?> getUserDictionary(@PathVariable Long user_id) {
    Optional<User> user = userRepository.findById(user_id);
    if (user.isEmpty()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    List<Dictionary> userDictionaries = dictionaryRepository.findDictionariesByUser(user.get());
    return ResponseEntity.ok().body(userDictionaries);
  }


  @PostMapping
  ResponseEntity<Dictionary> createDictionary(@Valid @RequestBody Dictionary dictionary) throws URISyntaxException {
    log.info("Request to create dictionary: {}", dictionary);

    Dictionary result = dictionaryRepository.save(dictionary);
    return ResponseEntity.created(new URI("/api/dictionaries/" + result.getUser().getUserId()
            + "/" + result.getEngWord().getWord()))
            .body(result);
  }
}
