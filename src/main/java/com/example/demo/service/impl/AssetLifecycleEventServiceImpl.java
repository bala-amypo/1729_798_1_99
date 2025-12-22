package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssetLifecycleEventServiceImpl
        implements AssetLifecycleEventService {

    private final AssetRepository assetRepo;
    private final AssetLifecycleEventRepository eventRepo;

    public AssetLifecycleEventServiceImpl(AssetRepository assetRepo,
                                          AssetLifecycleEventRepository eventRepo) {
        this.assetRepo = assetRepo;
        this.eventRepo = eventRepo;
    }

    public AssetLifecycleEvent logEvent(Long assetId, AssetLifecycleEvent event) {
        event.setAsset(assetRepo.findById(assetId).orElseThrow(() -> new ResourceNotFoundException("Event not found")););
        return eventRepo.save(event);
    }

    public List<AssetLifecycleEvent> getEventsForAsset(Long assetId) {
        return eventRepo.findByAssetId(assetId);
    }
}
