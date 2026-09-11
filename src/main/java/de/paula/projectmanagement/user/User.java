package de.paula.projectmanagement.user;

import de.paula.projectmanagement.project.Project;
import de.paula.projectmanagement.project.ProjectMember;
import de.paula.projectmanagement.task.Task;
import jakarta.persistence.*;

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

  @Column(length = 255, nullable = false)
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
}
