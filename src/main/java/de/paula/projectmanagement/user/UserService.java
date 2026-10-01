package de.paula.projectmanagement.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Provides business logic for user management.
 */
@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(
          UserRepository userRepository,
          PasswordEncoder passwordEncoder
  ) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * Creates a new user from the provided request and persists it.
   * The user's password is hashed before it is stored.
   *
   * @param request contains the data required to create the user
   * @return the persisted user
   * @throws IllegalArgumentException if the request is null
   * @throws UserAlreadyExistsException if the username or email already exists
   */
  public User createUser(CreateUserRequest request) {
    if (request == null) {
      throw new IllegalArgumentException("request must not be null");
    }

    if (userRepository.existsByEmail(request.email())) {
      throw new UserAlreadyExistsException(UserConflictField.EMAIL);
    }

    if (userRepository.existsByUsername(request.username()))  {
      throw new UserAlreadyExistsException(UserConflictField.USERNAME);
    }

    UUID id = UUID.randomUUID();

    User user = new User(
            id,
            request.username(),
            request.email(),
            passwordEncoder.encode(request.password()),
            request.firstName(),
            request.lastName(),
            LocalDateTime.now()
    );

    return userRepository.save(user);
  }

  /**
   * Finds a user by its id.
   *
   * @param id the id of the user to find
   * @return the found user
   * @throws IllegalArgumentException if id is null
   * @throws UserNotFoundException if no user with the given id exists
   */
  public User findUserById(UUID id) {
    if (id == null) {
      throw new IllegalArgumentException("id must not be null");
    }
      return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

  }