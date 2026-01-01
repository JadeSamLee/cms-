package com.cms.admin.messaging;

import com.cms.admin.workflow.domain.model.Complaint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * Publishes real-time events to connected Angular clients via WebSocket.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class RealTimeEventPublisher {

    private final SimpMessagingTemplate messagingTemplate;

    public void notifyComplaintUpdate(Complaint complaint) {
        messagingTemplate.convertAndSend("/topic/complaints", complaint);
        log.info("Real-time update sent for complaint {}", complaint.getTicketId());
    }

    public void notifySlaBreach(Complaint complaint) {
        messagingTemplate.convertAndSend("/topic/sla-breach", complaint);
        log.warn("SLA breach notification sent for {}", complaint.getTicketId());
    }
}
