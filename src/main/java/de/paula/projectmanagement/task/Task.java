package de.paula.projectmanagement.task;

import de.paula.projectmanagement.project.Project;
import de.paula.projectmanagement.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Task {

  @Id
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;

  @ManyToOne
  @JoinColumn(name = "assigned_to")
  private User assignedTo;

  @ManyToOne
  @JoinColumn(name = "created_by", nullable = false)
  private User createdBy;

  @Column(nullable = false)
  private String title;

  private String description;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  private LocalDate dueDate;

  private LocalDateTime completedAt;

  @Enumerated(EnumType.STRING)
  private TaskPriority priority;

  private Integer position;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TaskStatus status;
}
