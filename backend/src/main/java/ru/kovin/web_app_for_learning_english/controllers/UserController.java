package ru.kovin.web_app_for_learning_english.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kovin.web_app_for_learning_english.entities.User;
import ru.kovin.web_app_for_learning_english.repositories.UserRepository;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/users",
        produces = "application/json")
@CrossOrigin(origins = "*")
public class UserController {

  private final Logger log = LoggerFactory.getLogger(UserController.class);

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping
  public List<User> all() {
    return userRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> one(@PathVariable Long id) {
    Optional<User> userOptional = userRepository.findById(id);
    return userOptional.map(response -> ResponseEntity.ok().body(response))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PutMapping
  ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
    log.info("Request to update user: {}", user);

    User result = userRepository.save(user);
    return ResponseEntity.ok().body(result);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    log.info("Request to delete user: {}", id);
    userRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @PostMapping
  ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
    log.info("Request to create user: {}", user);

    User result = userRepository.save(user);
    return ResponseEntity.created(new URI("/api/users/" + result.getUserId()))
            .body(result);
  }
}
