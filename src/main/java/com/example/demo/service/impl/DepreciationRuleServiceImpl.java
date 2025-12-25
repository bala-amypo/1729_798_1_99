package com.example.demo.service.impl;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepreciationRuleServiceImpl implements DepreciationRuleService {

    @Autowired
    private DepreciationRuleRepository ruleRepository;

    @Override
    public DepreciationRule createRule(DepreciationRule rule) {
        if (rule.getUsefulLifeYears() == null || rule.getUsefulLifeYears() <= 0) {
            throw new RuntimeException("Invalid useful life");
        }
        if (rule.getSalvageValue() == null || rule.getSalvageValue() < 0) {
            throw new RuntimeException("Invalid salvage value");
        }
        return ruleRepository.save(rule);
    }

    @Override
    public List<DepreciationRule> getAllRules() {
        return ruleRepository.findAll();
    }
}
