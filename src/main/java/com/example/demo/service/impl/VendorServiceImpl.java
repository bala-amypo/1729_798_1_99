package com.example.demo.service.impl;

import com.example.demo.entity.Vendor;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository repository;

    public VendorServiceImpl(VendorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {
        if (repository.findByVendorName(vendor.getVendorName()).isPresent()) {
            throw new BadRequestException("Vendor name already exists");
        }
        return repository.save(vendor);
    }

    @Override
    public List<Vendor> getAllVendors() {
        return repository.findAll();
    }
}