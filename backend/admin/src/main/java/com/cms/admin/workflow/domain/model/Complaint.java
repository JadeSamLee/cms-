package com.cms.admin.workflow.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Core entity representing a complaint.
 * Unified model compatible with User and Support modules.
 */
@Entity
@Table(name = "complaints")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_id", unique = true, nullable = false)
    private String ticketId;

    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String category;

    @Column(name = "sub_category")
    private String subCategory;

    @Column(nullable = false)
    private String priority;

    private String status = "OPEN";

    private String description;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "assigned_agent_id")
    private Long assignedAgentId;

    @Column(name = "sla_response_deadline")
    private LocalDateTime slaResponseDeadline;

    @Column(name = "sla_resolution_deadline")
    private LocalDateTime slaResolutionDeadline;

    @Column(name = "sla_penalty_points")
    private Integer slaPenaltyPoints = 0;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
