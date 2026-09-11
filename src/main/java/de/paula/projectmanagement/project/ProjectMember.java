package de.paula.projectmanagement.project;

import de.paula.projectmanagement.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MapsId;

import java.time.LocalDateTime;

@Entity
public class ProjectMember {

  @EmbeddedId
  private ProjectMemberId id;

  @ManyToOne
  @MapsId("projectId")
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;

  @ManyToOne
  @MapsId("userId")
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ProjectRole role;

  @Column(nullable = false)
  private LocalDateTime joinedAt;
}
