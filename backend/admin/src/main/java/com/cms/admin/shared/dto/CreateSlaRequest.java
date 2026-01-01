package com.cms.admin.shared.dto;

import lombok.Data;

/**
 * DTO for creating a new SLA policy.
 */
@Data
public class CreateSlaRequest {
    private String category;
    private String priority;
    private Integer responseHours;
    private Integer resolutionHours;
}
