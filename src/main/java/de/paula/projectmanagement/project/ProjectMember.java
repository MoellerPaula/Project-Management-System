package de.paula.projectmanagement.project;

import de.paula.projectmanagement.user.User;
import jakarta.persistence.*;

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
