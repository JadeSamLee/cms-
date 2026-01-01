package com.cms.admin.sla.service;

import com.cms.admin.sla.domain.model.SlaPolicy;
import com.cms.admin.sla.domain.repository.SlaPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SlaPolicyService {
    private final SlaPolicyRepository repository;

    public List<SlaPolicy> getAllPolicies() {
        return repository.findAll();
    }

    public SlaPolicy createPolicy(SlaPolicy policy) {
        return repository.save(policy);
    }
}
