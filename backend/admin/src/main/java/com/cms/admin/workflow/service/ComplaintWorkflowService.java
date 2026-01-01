package com.cms.admin.workflow.service;

import com.cms.admin.messaging.RealTimeEventPublisher;
import com.cms.admin.shared.dto.CreateComplaintRequest;
import com.cms.admin.shared.exception.ResourceNotFoundException;
import com.cms.admin.governance.domain.repository.SupportTeamRepository;
import com.cms.admin.sla.domain.repository.SlaPolicyRepository;
import com.cms.admin.workflow.domain.model.Complaint;
import com.cms.admin.workflow.domain.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintWorkflowService {

    private final ComplaintRepository complaintRepository;
    private final SupportTeamRepository teamRepository;
    private final SlaPolicyRepository slaPolicyRepository;
    private final RealTimeEventPublisher eventPublisher;

    public Complaint createComplaint(CreateComplaintRequest request) {
        var slaPolicy = slaPolicyRepository.findByCategoryAndPriority(request.getCategory(), request.getPriority())
                .orElseThrow(() -> new ResourceNotFoundException("SLA policy not defined for " + request.getCategory() + " - " + request.getPriority()));

        var complaint = Complaint.builder()
                .ticketId(request.getTicketId())
                .userId(request.getUserId())
                .category(request.getCategory())
                .subCategory(request.getSubCategory())
                .priority(request.getPriority())
                .description(request.getDescription())
                .status("OPEN")
                .slaResponseDeadline(LocalDateTime.now().plusHours(slaPolicy.getResponseHours()))
                .slaResolutionDeadline(LocalDateTime.now().plusHours(slaPolicy.getResolutionHours()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        var saved = complaintRepository.save(complaint);
        eventPublisher.notifyComplaintUpdate(saved);
        return saved;
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public Complaint assignToTeam(Long complaintId, Long teamId) {
        var complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));
        teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found"));

        complaint.setDepartmentId(teamId);
        complaint.setStatus("ASSIGNED_TO_TEAM");
        complaint.setUpdatedAt(LocalDateTime.now());

        var saved = complaintRepository.save(complaint);
        eventPublisher.notifyComplaintUpdate(saved);
        return saved;
    }

    public Complaint verifyResolution(Long complaintId) {
        var complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));

        if (!"RESOLVED".equals(complaint.getStatus())) {
            throw new IllegalStateException("Complaint must be in RESOLVED state to verify");
        }

        complaint.setStatus("CLOSED");
        complaint.setUpdatedAt(LocalDateTime.now());

        var saved = complaintRepository.save(complaint);
        eventPublisher.notifyComplaintUpdate(saved);
        return saved;
    }

    public Complaint rejectResolution(Long complaintId, Long newTeamId) {
        var complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));

        teamRepository.findById(newTeamId)
                .orElseThrow(() -> new ResourceNotFoundException("New team not found"));

        complaint.setDepartmentId(newTeamId);
        complaint.setStatus("REASSIGNED");
        complaint.setUpdatedAt(LocalDateTime.now());

        var saved = complaintRepository.save(complaint);
        eventPublisher.notifyComplaintUpdate(saved);
        return saved;
    }
}
