package de.paula.projectmanagement.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @InjectMocks
  private UserService userService;

  @Test
  void shouldCreateUserWhenValidRequest() {
    CreateUserRequest request = new CreateUserRequest(
            "paula",
            "paula@example.com",
            "password123",
            "Paula",
            "Möller"
    );

    String hashedPassword = "hashedPassword123";

    when(passwordEncoder.encode(request.password()))
            .thenReturn(hashedPassword);

    User savedUser = new User(
            UUID.randomUUID(),
            "paula",
            "paula@example.com",
            hashedPassword,
            "Paula",
            "Möller",
            LocalDateTime.now()
    );

    when(userRepository.save(any(User.class)))
            .thenReturn(savedUser);

    User result = userService.createUser(request);

    assertSame(savedUser, result);
  }


}



