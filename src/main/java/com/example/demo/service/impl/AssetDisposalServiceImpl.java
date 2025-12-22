package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AssetDisposalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal) {

        Asset asset = assetRepo.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        disposal.setAsset(asset);
        return disposalRepo.save(disposal);
    }

    @Override
    @Transactional
    public AssetDisposal approveDisposal(Long disposalId, Long adminId) {

        AssetDisposal disposal = disposalRepo.findById(disposalId)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal not found"));

        User admin = userRepo.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));

        disposal.setApprovedBy(admin);

        Asset asset = disposal.getAsset();
        if (asset == null) {
            throw new ResourceNotFoundException("Asset not linked to disposal");
        }

        asset.setStatus("DISPOSED");
        assetRepo.save(asset);

        return disposalRepo.save(disposal);
    }
}
