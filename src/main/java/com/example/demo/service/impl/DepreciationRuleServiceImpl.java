package com.example.demo.service;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.DepreciationRuleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepreciationRuleServiceImpl implements DepreciationRuleService {

    private final DepreciationRuleRepository repository;

    public DepreciationRuleServiceImpl(DepreciationRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public DepreciationRule createRule(DepreciationRule rule) {
        if (rule.getUsefulLifeYears() <= 0) {
            throw new BadRequestException("Useful life years must be positive");
        }
        if (rule.getSalvageValue() < 0) {
            throw new BadRequestException("Salvage value cannot be negative");
        }
        if (!List.of("STRAIGHT_LINE", "DECLINING_BALANCE").contains(rule.getMethod())) {
            throw new BadRequestException("Invalid depreciation method");
        }
        return repository.save(rule);
    }

    @Override
    public List<DepreciationRule> getAllRules() {
        return repository.findAll();
    }
}