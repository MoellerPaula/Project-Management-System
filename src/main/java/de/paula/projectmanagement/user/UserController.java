package de.paula.projectmanagement.user;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  public UserController(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  @PostMapping
  public ResponseEntity<UserResponse> createUser(
          @Valid @RequestBody CreateUserRequest request
  ) {
    User user = userService.createUser(request);
    UserResponse response = userMapper.toResponse(user);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
  }

}