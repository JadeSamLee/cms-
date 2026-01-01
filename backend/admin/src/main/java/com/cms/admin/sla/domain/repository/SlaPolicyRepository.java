package com.cms.admin.sla.domain.repository;

import com.cms.admin.sla.domain.model.SlaPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SlaPolicyRepository extends JpaRepository<SlaPolicy, Long> {
    Optional<SlaPolicy> findByCategoryAndPriority(String category, String priority);
}
