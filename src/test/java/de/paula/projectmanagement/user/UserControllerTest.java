package de.paula.projectmanagement.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockitoBean
  private UserService userService;

  @MockitoBean
  private UserMapper userMapper;

  @Test
  void shouldCreateUserWhenRequestIsValid() throws Exception {
    CreateUserRequest request = new CreateUserRequest(
            "paula",
            "paula@email.com",
            "Password123!",
            "Paula",
            "Möller"
    );

    UUID id = UUID.randomUUID();
    LocalDateTime dateTime = LocalDateTime.now();

    User user = new User(
            id,
            "paula",
            "paula@email.com",
            "hashedPassword",
            "Paula",
            "Möller",
            dateTime
    );

    UserResponse response = new UserResponse(
            id,
            "paula",
            "paula@email.com",
            "Paula",
            "Möller",
            dateTime
    );

    when(userService.createUser(request)).thenReturn(user);

    when(userMapper.toResponse(user)).thenReturn(response);

    MvcResult result = mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                    {
                    "username": "paula",
                    "email": "paula@email.com",
                    "password": "Password123!",
                    "firstName": "Paula",
                    "lastName": "Möller"
                    }
                    """))
            .andExpect(status().isCreated())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(id.toString()))
            .andExpect(jsonPath("$.username").value("paula"))
            .andExpect(jsonPath("$.email").value("paula@email.com"))
            .andExpect(jsonPath("$.firstName").value("Paula"))
            .andExpect(jsonPath("$.lastName").value("Möller"))
            .andExpect(jsonPath("$.passwordHash").doesNotExist())
            .andReturn();

    UserResponse actualResponse = objectMapper.readValue(
            result.getResponse().getContentAsString(),
            UserResponse.class
    );

    assertEquals(dateTime, actualResponse.createdAt());
  }

  @Test
  void shouldReturnBadRequestWhenUsernameIsBlank() throws Exception{
    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                    {
                    "username": "",
                    "email": "paula@email.com",
                    "password": "Password123!",
                    "firstName": "Paula",
                    "lastName": "Möller"
                    }
                    """))
            .andExpect(status().isBadRequest());

    verify(userService, never()).createUser(any(CreateUserRequest.class));
  }

  @Test
  void shouldReturnUnsupportedMediaTypeWhenContentTypeIsIncorrect() throws Exception{
    mockMvc.perform(post("/users")
                    .contentType(MediaType.TEXT_PLAIN)
                    .content("""
                    {
                    "username": "paula",
                    "email": "paula@email.com",
                    "password": "Password123!",
                    "firstName": "Paula",
                    "lastName": "Möller"
                    }
                    """))
            .andExpect(status().isUnsupportedMediaType());

    verify(userService, never()).createUser(any(CreateUserRequest.class));
  }

  @Test
  void shouldReturnBadRequestWhenEmailIsInvalid() throws Exception{
    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                    {
                    "username": "paula",
                    "email": "paulaaemail.com",
                    "password": "Password123!",
                    "firstName": "Paula",
                    "lastName": "Möller"
                    }
                    """))
            .andExpect(status().isBadRequest());

    verify(userService, never()).createUser(any(CreateUserRequest.class));
  }
}