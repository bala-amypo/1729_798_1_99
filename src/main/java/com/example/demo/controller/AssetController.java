package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import com.example.demo.repository.AssetRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService service;
    private final AssetRepository assetRepository;

    public AssetController(AssetService service, AssetRepository assetRepository) {
        this.service = service;
        this.assetRepository = assetRepository;
    }

    // CREATE
    @PostMapping("/{vendorId}/{ruleId}")
    public Asset createAsset(
            @PathVariable Long vendorId,
            @PathVariable Long ruleId,
            @RequestBody Asset asset) {
        return service.createAsset(vendorId, ruleId, asset);
    }

    // GET ALL
    @GetMapping
    public List<Asset> getAllAssets() {
        return service.getAllAssets();
    }

    // GET BY ID
    @GetMapping("/{assetId}")
    public Asset getAssetById(@PathVariable Long assetId) {
        return service.getAssetById(assetId);
    }

    // GET BY STATUS
    @GetMapping("/status/{status}")
    public List<Asset> getAssetsByStatus(@PathVariable String status) {
        return assetRepository.findByStatus(status);
    }
}