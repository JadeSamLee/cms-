package com.cms.admin.workflow.domain.repository;

import com.cms.admin.workflow.domain.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Query("SELECT c FROM Complaint c WHERE " +
            "(c.status IN ('OPEN', 'ASSIGNED_TO_TEAM') AND c.slaResponseDeadline < CURRENT_TIMESTAMP) OR " +
            "(c.status IN ('IN_PROGRESS', 'RESOLVED') AND c.slaResolutionDeadline < CURRENT_TIMESTAMP)")
    List<Complaint> findSlaBreachedComplaints();
}
