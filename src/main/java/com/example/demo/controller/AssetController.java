package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService service;

    public AssetController(AssetService service) {
        this.service = service;
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
}
