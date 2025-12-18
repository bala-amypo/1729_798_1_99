package com.example.demo.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AssetDisposalService;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {

    @Autowired
    private AssetRepository assetRepo;

    @Autowired
    private AssetDisposalRepository disposalRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal) {

        Asset asset = assetRepo.findById(assetId).orElseThrow();
        disposal.setAsset(asset);
        disposal.setCreatedAt(LocalDateTime.now());

        return disposalRepo.save(disposal);
    }

    @Override
    public AssetDisposal approveDisposal(Long disposalId, Long adminId) {

        AssetDisposal disposal = disposalRepo.findById(disposalId).orElseThrow();
        User admin = userRepo.findById(adminId).orElseThrow();

        disposal.setApprovedBy(admin);

        Asset asset = disposal.getAsset();
        asset.setStatus("DISPOSED");
        assetRepo.save(asset);

        return disposalRepo.save(disposal);
    }
}
