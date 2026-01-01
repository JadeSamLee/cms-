package com.cms.admin.governance.api;

import com.cms.admin.governance.domain.model.SupportTeam;
import com.cms.admin.governance.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST API for managing support teams.
 */
@RestController
@RequestMapping("/api/v1/admin/teams")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TeamController {
    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<SupportTeam>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @PostMapping
    public ResponseEntity<SupportTeam> createTeam(@RequestBody SupportTeam team) {
        return ResponseEntity.ok(teamService.createTeam(team));
    }
}
