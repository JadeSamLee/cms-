package com.cms.admin.scheduling;

import com.cms.admin.messaging.RealTimeEventPublisher;
import com.cms.admin.workflow.domain.model.Complaint;
import com.cms.admin.workflow.domain.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * Scheduled task that checks for SLA breaches every minute.
 * Applies penalty and notifies via WebSocket.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class SlaBreachScheduler {

    private final ComplaintRepository complaintRepository;
    private final RealTimeEventPublisher eventPublisher;

    @Scheduled(cron = "${app.sla.cron}")
    public void detectAndPenalizeBreaches() {
        log.info("Running SLA breach check at {}", LocalDateTime.now());

        var breached = complaintRepository.findSlaBreachedComplaints();

        for (Complaint c : breached) {
            c.setSlaPenaltyPoints(c.getSlaPenaltyPoints() + 10);
            c.setUpdatedAt(LocalDateTime.now());
            complaintRepository.save(c);

            eventPublisher.notifySlaBreach(c);
            log.warn("SLA BREACH: {} | New penalty: {}", c.getTicketId(), c.getSlaPenaltyPoints());
        }

        if (breached.isEmpty()) {
            log.info("No SLA breaches found.");
        }
    }
}
