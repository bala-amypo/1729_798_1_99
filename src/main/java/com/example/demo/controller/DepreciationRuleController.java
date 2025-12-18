package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.service.DepreciationRuleService;

@RestController
@RequestMapping("/rules")
public class DepreciationRuleController {

    @Autowired
    private DepreciationRuleService ruleService;

    @PostMapping
    public DepreciationRule create(@RequestBody DepreciationRule rule) {
        return ruleService.save(rule);
    }

    @GetMapping
    public List<DepreciationRule> getAll() {
        return ruleService.getAll();
    }

    @GetMapping("/{id}")
    public DepreciationRule getById(@PathVariable Long id) {
        return ruleService.getById(id);
    }
}
