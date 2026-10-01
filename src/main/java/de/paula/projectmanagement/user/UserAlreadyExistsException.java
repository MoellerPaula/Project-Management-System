package de.paula.projectmanagement.user;

public class UserAlreadyExistsException extends RuntimeException {

  private final UserConflictField conflictField;

  public UserAlreadyExistsException(UserConflictField conflictField) {
    super("User with " + conflictField.name().toLowerCase() + " already exists");
    this.conflictField = conflictField;
  }

  public UserConflictField getConflictField() {
    return conflictField;
  }
}
