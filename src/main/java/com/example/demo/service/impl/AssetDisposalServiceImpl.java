package com.example.demo.service;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {

    private final AssetDisposalRepository disposalRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public AssetDisposalServiceImpl(AssetDisposalRepository disposalRepository, 
                                   AssetRepository assetRepository, UserRepository userRepository) {
        this.disposalRepository = disposalRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal) {
        var asset = assetRepository.findById(assetId)
            .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        
        // Create new disposal object to avoid any issues with incoming data
        AssetDisposal newDisposal = new AssetDisposal();
        newDisposal.setDisposalMethod(disposal.getDisposalMethod());
        newDisposal.setDisposalValue(disposal.getDisposalValue());
        newDisposal.setDisposalDate(disposal.getDisposalDate());
        newDisposal.setAsset(asset);
        newDisposal.setApprovedBy(null);
        
        return disposalRepository.save(newDisposal);
    }

    @Override
    public AssetDisposal approveDisposal(Long disposalId, Long adminId) {
        var disposal = disposalRepository.findById(disposalId)
            .orElseThrow(() -> new ResourceNotFoundException("Disposal not found"));
        var admin = userRepository.findById(adminId)
            .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
        
        disposal.setApprovedBy(admin);
        disposal.getAsset().setStatus("DISPOSED");
        assetRepository.save(disposal.getAsset());
        
        return disposalRepository.save(disposal);
    }
}