package de.paula.projectmanagement.user;

import java.util.UUID;

/**
 * Exception that is thrown when a user is not found.
 */
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(UUID id) {
    super("User with id " + id + " not found");
  }

}
