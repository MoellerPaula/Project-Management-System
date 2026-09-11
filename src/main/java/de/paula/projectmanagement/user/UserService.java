package de.paula.projectmanagement.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

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

  public User createUser(CreateUserRequest request) {
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


}
