package com.example.demo.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.service.DepreciationRuleService;

@Service
public class DepreciationRuleServiceImpl implements DepreciationRuleService {

    @Autowired
    private DepreciationRuleRepository ruleRepo;

    @Override
    public DepreciationRule createRule(DepreciationRule rule) {

        if (rule.getUsefulLifeYears() <= 0) {
            throw new RuntimeException("Useful life must be > 0");
        }

        rule.setCreatedAt(LocalDateTime.now());
        return ruleRepo.save(rule);
    }
}
