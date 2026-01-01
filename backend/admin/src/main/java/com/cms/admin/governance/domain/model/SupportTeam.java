package com.cms.admin.governance.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

/**
 * Entity representing a support team (e.g., IT Support, Finance).
 */
@Entity
@Table(name = "support_teams")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class SupportTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
