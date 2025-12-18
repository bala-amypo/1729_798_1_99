package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Asset;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.entity.Vendor;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.AssetService;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepo;

    @Autowired
    private VendorRepository vendorRepo;

    @Autowired
    private DepreciationRuleRepository ruleRepo;

    @Override
    public Asset createAsset(Long vendorId, Long ruleId, Asset asset) {

        if (asset.getPurchaseCost() <= 0) {
            throw new RuntimeException("Purchase cost must be positive");
        }

        Vendor vendor = vendorRepo.findById(vendorId).orElseThrow();
        DepreciationRule rule = ruleRepo.findById(ruleId).orElseThrow();

        asset.setVendor(vendor);
        asset.setDepreciationRule(rule);
        asset.setStatus("ACTIVE");
        asset.setCreatedAt(LocalDateTime.now());

        return assetRepo.save(asset);
    }

    @Override
    public List<Asset> getAssetsByStatus(String status) {
        return assetRepo.findByStatus(status);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepo.findAll();
    }

    @Override
    public Asset getAsset(Long id) {
        return assetRepo.findById(id).orElse(null);
    }
}
