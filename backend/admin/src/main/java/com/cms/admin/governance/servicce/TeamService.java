package com.cms.admin.governance.service;

import com.cms.admin.governance.domain.model.SupportTeam;
import com.cms.admin.governance.domain.repository.SupportTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service layer for managing support teams.
 */
@Service
@RequiredArgsConstructor
public class TeamService {
    private final SupportTeamRepository repository;

    public List<SupportTeam> getAllTeams() {
        return repository.findAll();
    }

    public SupportTeam createTeam(SupportTeam team) {
        return repository.save(team);
    }
}
