package com.example.demo.service.impl;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.service.AssetDisposalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {

    private final AssetDisposalRepository disposalRepo;

    public AssetDisposalServiceImpl(AssetDisposalRepository disposalRepo) {
        this.disposalRepo = disposalRepo;
    }

    @Override
    public List<AssetDisposal> getAllDisposals() {
        return disposalRepo.findAll();
    }

    @Override
    public List<AssetDisposal> getDisposalsByApprover(User user) {
        return disposalRepo.findByApprovedBy(user);
    }

    @Override
    public AssetDisposal getByAssetId(Long assetId) {
        return disposalRepo.findByAsset_Id(assetId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(
                        "AssetDisposal not found for assetId " + assetId));
    }

    @Override
    public AssetDisposal createDisposal(AssetDisposal assetDisposal) {
        return disposalRepo.save(assetDisposal);
    }

    @Override
    public AssetDisposal updateDisposal(Long id, AssetDisposal updatedDisposal) {
        AssetDisposal existing = disposalRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "AssetDisposal not found with id " + id));

        existing.setAsset(updatedDisposal.getAsset());
        existing.setDisposalMethod(updatedDisposal.getDisposalMethod());
        existing.setDisposalValue(updatedDisposal.getDisposalValue());
        existing.setDisposalDate(updatedDisposal.getDisposalDate());
        existing.setApprovedBy(updatedDisposal.getApprovedBy());

        return disposalRepo.save(existing);
    }

    @Override
    public void deleteDisposal(Long id) {
        AssetDisposal existing = disposalRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "AssetDisposal not found with id " + id));
        disposalRepo.delete(existing);
    }
}
