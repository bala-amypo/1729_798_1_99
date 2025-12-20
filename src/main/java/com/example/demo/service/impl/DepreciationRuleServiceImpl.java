package com.example.demo.service.impl;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepreciationRuleServiceImpl implements DepreciationRuleService {

    private final DepreciationRuleRepository repo;

    public DepreciationRuleServiceImpl(DepreciationRuleRepository repo) {
        this.repo = repo;
    }

    public DepreciationRule createRule(DepreciationRule rule) {
        return repo.save(rule);
    }

    public List<DepreciationRule> getAllRules() {
        return repo.findAll();
    }
}
