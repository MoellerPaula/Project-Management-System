package de.paula.projectmanagement.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank
        @Size(max = 50)
        String username,

        @NotBlank
        @Email
        @Size(max = 254)
        String email,

        @NotBlank
        String password,

        @NotBlank
        @Size(max = 100)
        String firstName,

        @NotBlank
        @Size(max = 100)
        String lastName
) {
}
