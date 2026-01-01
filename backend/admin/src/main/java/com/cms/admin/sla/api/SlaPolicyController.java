package com.cms.admin.sla.api;

import com.cms.admin.sla.domain.model.SlaPolicy;
import com.cms.admin.sla.service.SlaPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/sla")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class SlaPolicyController {
    private final SlaPolicyService service;

    @GetMapping
    public ResponseEntity<List<SlaPolicy>> getPolicies() {
        return ResponseEntity.ok(service.getAllPolicies());
    }

    @PostMapping
    public ResponseEntity<SlaPolicy> createPolicy(@RequestBody SlaPolicy policy) {
        return ResponseEntity.ok(service.createPolicy(policy));
    }
}
