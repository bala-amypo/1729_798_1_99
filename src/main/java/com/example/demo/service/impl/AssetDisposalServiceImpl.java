package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.AssetDisposalService;
import org.springframework.stereotype.Service;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {

    private final AssetRepository assetRepo;
    private final AssetDisposalRepository disposalRepo;
    private final UserRepository userRepo;

    public AssetDisposalServiceImpl(AssetRepository assetRepo,
                                    AssetDisposalRepository disposalRepo,
                                    UserRepository userRepo) {
        this.assetRepo = assetRepo;
        this.disposalRepo = disposalRepo;
        this.userRepo = userRepo;
    }

    public AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal) {
        disposal.setAsset(assetRepo.findById(assetId).orElseThrow());
        return disposalRepo.save(disposal);
    }

    public AssetDisposal approveDisposal(Long disposalId, Long adminId) {
        AssetDisposal disposal = disposalRepo.findById(disposalId).orElseThrow();
        disposal.setApprovedBy(userRepo.findById(adminId).orElseThrow());

        Asset asset = disposal.getAsset();
        asset.setStatus("DISPOSED");
        assetRepo.save(asset);

        return disposalRepo.save(disposal);
    }
}
