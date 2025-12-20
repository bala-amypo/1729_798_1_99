package com.example.demo.service.impl;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepo;

    public VendorServiceImpl(VendorRepository vendorRepo) {
        this.vendorRepo = vendorRepo;
    }

    public Vendor createVendor(Vendor vendor) {
        return vendorRepo.save(vendor);
    }

    public List<Vendor> getAllVendors() {
        return vendorRepo.findAll();
    }
}
