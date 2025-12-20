package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepo;
    private final VendorRepository vendorRepo;
    private final DepreciationRuleRepository ruleRepo;

    public AssetServiceImpl(AssetRepository assetRepo,
                            VendorRepository vendorRepo,
                            DepreciationRuleRepository ruleRepo) {
        this.assetRepo = assetRepo;
        this.vendorRepo = vendorRepo;
        this.ruleRepo = ruleRepo;
    }

    public Asset createAsset(Long vendorId, Long ruleId, Asset asset) {
        if (asset.getPurchaseCost() <= 0)
            throw new RuntimeException("Invalid purchase cost");

        asset.setVendor(vendorRepo.findById(vendorId).orElseThrow());
        asset.setDepreciationRule(ruleRepo.findById(ruleId).orElseThrow());
        asset.setStatus("ACTIVE");

        return assetRepo.save(asset);
    }

    public List<Asset> getAllAssets() {
        return assetRepo.findAll();
    }

    public List<Asset> getAssetsByStatus(String status) {
        return assetRepo.findByStatus(status);
    }

    public Asset getAsset(Long id) {
        return assetRepo.findById(id).orElseThrow();
    }
}
