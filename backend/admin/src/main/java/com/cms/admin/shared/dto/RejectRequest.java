package com.cms.admin.shared.dto;

import lombok.Data;

/**
 * DTO for rejecting a resolution and reassigning to a new team.
 */
@Data
public class RejectRequest {
    private Long newTeamId;
}
