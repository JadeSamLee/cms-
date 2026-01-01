package com.cms.admin.sla.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

/**
 * Entity representing an SLA policy per category and priority.
 */
@Entity
@Table(name = "sla_policies", uniqueConstraints = @UniqueConstraint(columnNames = {"category", "priority"}))
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class SlaPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String priority;

    @Column(name = "response_hours", nullable = false)
    private Integer responseHours;

    @Column(name = "resolution_hours", nullable = false)
    private Integer resolutionHours;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
