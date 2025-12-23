package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AssetDisposalService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {

    private final AssetRepository assetRepo;
    private final AssetDisposalRepository disposalRepo;
    private final UserRepository userRepo;

    public AssetDisposalServiceImpl(
            AssetRepository assetRepo,
            AssetDisposalRepository disposalRepo,
            UserRepository userRepo) {
        this.assetRepo = assetRepo;
        this.disposalRepo = disposalRepo;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal) {
        Asset asset = assetRepo.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id: " + assetId));

        if ("DISPOSED".equals(asset.getStatus())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Asset is already DISPOSED");
        }

        if (disposalRepo.findByAssetId(assetId).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This asset already has a disposal record");
        }

        disposal.setId(null);
        disposal.setAsset(asset);
        disposal.setRequestDate(LocalDate.now());
        disposal.setApprovedBy(null);

        return disposalRepo.save(disposal);
    }

    @Override
    @Transactional
    public AssetDisposal approveDisposal(Long disposalId, Long adminId) {
        AssetDisposal disposal = disposalRepo.findById(disposalId)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal record not found"));

        User admin = userRepo.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin user not found"));

        Asset asset = disposal.getAsset();
        asset.setStatus("DISPOSED");
        assetRepo.save(asset);

        disposal.setApprovedBy(admin);
        disposal.setApprovalDate(LocalDate.now());

        return disposalRepo.save(disposal);
    }
}
