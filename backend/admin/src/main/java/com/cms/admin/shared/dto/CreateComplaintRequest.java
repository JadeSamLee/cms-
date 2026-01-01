package com.cms.admin.shared.dto;

import lombok.Data;

/**
 * DTO used when receiving a new complaint from the User module.
 */
@Data
public class CreateComplaintRequest {
    private String ticketId;
    private Long userId;
    private String category;
    private String subCategory;
    private String priority;
    private String description;
}
