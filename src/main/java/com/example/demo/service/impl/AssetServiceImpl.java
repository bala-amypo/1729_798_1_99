package com.example.demo.service;

import com.example.demo.entity.Asset;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.DepreciationRuleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final VendorRepository vendorRepository;
    private final DepreciationRuleRepository ruleRepository;

    public AssetServiceImpl(AssetRepository assetRepository, VendorRepository vendorRepository, 
                           DepreciationRuleRepository ruleRepository) {
        this.assetRepository = assetRepository;
        this.vendorRepository = vendorRepository;
        this.ruleRepository = ruleRepository;
    }

    @Override
    public Asset createAsset(Long vendorId, Long ruleId, Asset asset) {
        if (asset.getPurchaseCost() < 0) {
            throw new RuntimeException("Purchase cost cannot be negative");
        }
        
        var vendor = vendorRepository.findById(vendorId)
            .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));
        var rule = ruleRepository.findById(ruleId)
            .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
        
        if (assetRepository.existsByAssetTag(asset.getAssetTag())) {
            throw new BadRequestException("Asset tag already exists");
        }
        
        asset.setVendor(vendor);
        asset.setDepreciationRule(rule);
        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getAssetById(Long id) {
        return assetRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
    }
}