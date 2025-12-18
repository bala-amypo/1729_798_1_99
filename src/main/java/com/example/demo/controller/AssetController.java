package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Asset;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.AssetService;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private VendorRepository vendorRepo;

    @Autowired
    private DepreciationRuleRepository ruleRepo;

    @PostMapping("/{vendorId}/{ruleId}")
    public Asset createAsset(@PathVariable Long vendorId,
                             @PathVariable Long ruleId,
                             @RequestBody Asset asset) {

        asset.setVendor(vendorRepo.findById(vendorId).orElse(null));
        asset.setDepreciationRule(ruleRepo.findById(ruleId).orElse(null));

        return assetService.saveAsset(asset);
    }

    @GetMapping
    public List<Asset> getAll() {
        return assetService.getAllAssets();
    }

    @GetMapping("/{id}")
    public Asset getById(@PathVariable Long id) {
        return assetService.getAsset(id);
    }
}
