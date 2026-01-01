package com.cms.admin.governance.domain.repository;

import com.cms.admin.governance.domain.model.SupportTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportTeamRepository extends JpaRepository<SupportTeam, Long> {
}
