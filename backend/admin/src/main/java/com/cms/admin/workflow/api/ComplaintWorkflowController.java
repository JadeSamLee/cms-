package com.cms.admin.workflow.api;

import com.cms.admin.shared.dto.CreateComplaintRequest;
import com.cms.admin.workflow.domain.model.Complaint;
import com.cms.admin.workflow.service.ComplaintWorkflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ComplaintWorkflowController {

    private final ComplaintWorkflowService service;

    // Receive new complaint from User module
    @PostMapping("/complaints")
    public ResponseEntity<Complaint> receiveComplaint(@RequestBody CreateComplaintRequest request) {
        return ResponseEntity.ok(service.createComplaint(request));
    }

    // Admin dashboard - list all complaints
    @GetMapping("/complaints")
    public ResponseEntity<List<Complaint>> getAllComplaints() {
        return ResponseEntity.ok(service.getAllComplaints());
    }

    // Assign to team
    @PostMapping("/workflow/complaints/{id}/assign/{teamId}")
    public ResponseEntity<Complaint> assignToTeam(@PathVariable Long id, @PathVariable Long teamId) {
        return ResponseEntity.ok(service.assignToTeam(id, teamId));
    }

    // Verify resolution
    @PostMapping("/workflow/complaints/{id}/verify")
    public ResponseEntity<Complaint> verify(@PathVariable Long id) {
        return ResponseEntity.ok(service.verifyResolution(id));
    }

    // Reject and reassign
    @PostMapping("/workflow/complaints/{id}/reject")
    public ResponseEntity<Complaint> reject(@PathVariable Long id, @RequestBody RejectRequest request) {
        return ResponseEntity.ok(service.rejectResolution(id, request.getNewTeamId()));
    }
}
