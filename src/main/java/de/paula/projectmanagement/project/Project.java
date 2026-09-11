package de.paula.projectmanagement.project;

import de.paula.projectmanagement.task.Task;
import de.paula.projectmanagement.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
public class Project {

  @Id
  private UUID id;

  @Column(nullable = false)
  private String name;

  private String description;

  @ManyToOne
  @JoinColumn(name = "created_by", nullable = false)
  private User createdBy;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  private LocalDateTime completedAt;

  private LocalDate startDate;

  private LocalDate dueDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ProjectStatus status;

  @OneToMany(mappedBy = "project")
  private Set<ProjectMember> members;

  @OneToMany(mappedBy = "project")
  private Set<Task> tasks;






}