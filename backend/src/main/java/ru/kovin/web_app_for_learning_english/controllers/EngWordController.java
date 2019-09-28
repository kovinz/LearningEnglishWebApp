package ru.kovin.web_app_for_learning_english.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kovin.web_app_for_learning_english.entities.EngWord;
import ru.kovin.web_app_for_learning_english.repositories.EngWordRepository;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class EngWordController {

  private final Logger log = LoggerFactory.getLogger(EngWordController.class);

  private final EngWordRepository engWordRepository;

  public EngWordController(EngWordRepository engWordRepository) {
    this.engWordRepository = engWordRepository;
  }

  @GetMapping("/eng_words")
  public List<EngWord> all() {
    return engWordRepository.findAll();
  }

  @GetMapping("/eng_words/{id}")
  public ResponseEntity<?> one(@PathVariable Long id) {
    Optional<EngWord> engWordOptional = engWordRepository.findById(id);
    return engWordOptional.map(response -> ResponseEntity.ok().body(response))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PutMapping("/eng_words")
  ResponseEntity<EngWord> updateEngWord(@Valid @RequestBody EngWord engWord) {
    log.info("Request to update eng_word: {}", engWord);

    EngWord result = engWordRepository.save(engWord);
    return ResponseEntity.ok().body(result);
  }

  @DeleteMapping("/eng_words/{id}")
  public ResponseEntity<?> deleteEngWord(@PathVariable Long id) {
    log.info("Request to delete eng_word: {}", id);
    engWordRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/eng_words")
  ResponseEntity<EngWord> createEngWord(@Valid @RequestBody EngWord engWord) throws URISyntaxException {
    log.info("Request to create eng_word: {}", engWord);

    EngWord result = engWordRepository.save(engWord);
    return ResponseEntity.created(new URI("/api/eng_words/" + result.getEngWordId()))
            .body(result);
  }
}
