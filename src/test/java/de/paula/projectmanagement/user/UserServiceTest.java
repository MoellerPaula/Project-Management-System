package de.paula.projectmanagement.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

  @Test
  void shouldThrowIllegalArgumentExceptionWhenRequestIsNull() {
    assertThrows(
            IllegalArgumentException.class,
            () -> userService.createUser(null)
    );
  }

  @Test
  void shouldThrowUserAlreadyExistsExceptionWhenEmailAlreadyExists() {

    CreateUserRequest request = new CreateUserRequest(
            "paula",
            "paula@example.com",
            "password123",
            "Paula",
            "Möller"
    );

    when(userRepository.existsByEmail(request.email())).thenReturn(true);

    UserAlreadyExistsException exception = assertThrows(
            UserAlreadyExistsException.class,
            () -> userService.createUser(request)
    );

    assertEquals(UserConflictField.EMAIL, exception.getConflictField());
  }

  @Test
  void shouldThrowUserAlreadyExistsExceptionWhenUsernameAlreadyExists() {

    CreateUserRequest request = new CreateUserRequest(
            "paula",
            "paula@example.com",
            "password123",
            "Paula",
            "Möller"
    );

    when(userRepository.existsByEmail(request.email())).thenReturn(false);
    when(userRepository.existsByUsername(request.username())).thenReturn(true);

    UserAlreadyExistsException exception = assertThrows(
            UserAlreadyExistsException.class,
            () -> userService.createUser(request)
    );

    assertEquals(UserConflictField.USERNAME, exception.getConflictField());
  }

  @Test
  void shouldFindUserByIdWhenUserExists() {
    UUID userId = UUID.randomUUID();

    String hashedPassword = "hashedPassword123";

    User user = new User(
            userId,
            "paula",
            "paula@example.com",
            hashedPassword,
            "Paula",
            "Möller",
            LocalDateTime.now()
    );

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));

    User result = userService.findUserById(userId);

    assertSame(user, result);
  }

  @Test
  void shouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
    UUID userId = UUID.randomUUID();

    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    assertThrows(UserNotFoundException.class, () -> userService.findUserById(userId));
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenIdIsNull() {
    UUID userId = null;

    assertThrows(IllegalArgumentException.class, () -> userService.findUserById(userId));
  }
}



