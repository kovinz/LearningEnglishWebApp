package ru.kovin.web_app_for_learning_english.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kovin.web_app_for_learning_english.entities.RuWord;
import ru.kovin.web_app_for_learning_english.repositories.RuWordRepository;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class RuWordController {

  private final Logger log = LoggerFactory.getLogger(RuWordController.class);

  private final RuWordRepository ruWordRepository;

  public RuWordController(RuWordRepository ruWordRepository) {
    this.ruWordRepository = ruWordRepository;
  }

  @GetMapping("/ru_words")
  public List<RuWord> all() {
    return ruWordRepository.findAll();
  }

  @GetMapping("/ru_words/{id}")
  public ResponseEntity<?> one(@PathVariable Long id) {
    Optional<RuWord> ruWordOptional = ruWordRepository.findById(id);
    return ruWordOptional.map(response -> ResponseEntity.ok().body(response))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PutMapping("/ru_words")
  ResponseEntity<RuWord> updateRuWord(@Valid @RequestBody RuWord ruWord) {
    log.info("Request to update ru_word: {}", ruWord);

    RuWord result = ruWordRepository.save(ruWord);
    return ResponseEntity.ok().body(result);
  }

  @DeleteMapping("/ru_words/{id}")
  public ResponseEntity<?> deleteRuWord(@PathVariable Long id) {
    log.info("Request to delete ru_word: {}", id);
    ruWordRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/ru_words")
  ResponseEntity<RuWord> createRuWord(@Valid @RequestBody RuWord ruWord) throws URISyntaxException {
    log.info("Request to create ru_word: {}", ruWord);

    RuWord result = ruWordRepository.save(ruWord);
    return ResponseEntity.created(new URI("/api/ru_words/" + result.getRuWordId()))
            .body(result);
  }
}
