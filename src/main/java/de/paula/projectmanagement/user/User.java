package de.paula.projectmanagement.user;

import de.paula.projectmanagement.project.Project;
import de.paula.projectmanagement.project.ProjectMember;
import de.paula.projectmanagement.task.Task;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "app_user")
public class User {

  @Id
  private UUID id;

  @Column(length = 50, nullable = false, unique = true)
  private String username;

  @Column(length = 254, nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String passwordHash;

  @Column(length = 100, nullable = false)
  private String firstName;

  @Column(length = 100, nullable = false)
  private String lastName;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "createdBy")
  private Set<Project> createdProjects;

  @OneToMany(mappedBy = "user")
  private Set<ProjectMember> projectMemberships;

  @OneToMany(mappedBy = "createdBy")
  private Set<Task> createdTasks;

  @OneToMany(mappedBy = "assignedTo")
  private Set<Task> assignedTasks;


  protected User() { }

  public User(
          UUID id,
          String username,
          String email,
          String passwordHash,
          String firstName,
          String lastName,
          LocalDateTime createdAt
  ) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.passwordHash = passwordHash;
    this.firstName = firstName;
    this.lastName = lastName;
    this.createdAt = createdAt;
  }

}
