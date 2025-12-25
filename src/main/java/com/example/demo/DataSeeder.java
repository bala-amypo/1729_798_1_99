package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Set;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final VendorRepository vendorRepository;
    private final DepreciationRuleRepository ruleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(RoleRepository roleRepository,
                      UserRepository userRepository,
                      VendorRepository vendorRepository,
                      DepreciationRuleRepository ruleRepository,
                      PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.vendorRepository = vendorRepository;
        this.ruleRepository = ruleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Seed roles
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseGet(() -> roleRepository.save(new Role("ADMIN")));
        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> roleRepository.save(new Role("USER")));

        // Seed users
        if (!userRepository.existsByEmail("integration_admin@example.com")) {
            User admin = new User();
            admin.setName("Integration Admin");
            admin.setEmail("integration_admin@example.com");
            admin.setPassword(passwordEncoder.encode("adminpass"));
            admin.setRoles(Set.of(adminRole));
            admin.setCreatedAt(LocalDateTime.now());
            userRepository.save(admin);
        }

        if (!userRepository.existsByEmail("integration_user@example.com")) {
            User user = new User();
            user.setName("Integration User");
            user.setEmail("integration_user@example.com");
            user.setPassword(passwordEncoder.encode("userpass"));
            user.setRoles(Set.of(userRole));
            user.setCreatedAt(LocalDateTime.now());
            userRepository.save(user);
        }

        // Seed vendor
        if (!vendorRepository.existsByVendorName("IntegrationVendor")) {
            Vendor vendor = new Vendor();
            vendor.setVendorName("IntegrationVendor");
            vendor.setContactEmail("vendor@example.com");
            vendor.setCreatedAt(LocalDateTime.now());
            vendorRepository.save(vendor);
        }

        // Seed depreciation rule
        if (!ruleRepository.existsByRuleName("IntegrationRule")) {
            DepreciationRule rule = new DepreciationRule();
            rule.setRuleName("IntegrationRule");
            rule.setMethod("STRAIGHT_LINE");
            rule.setUsefulLifeYears(5);
            rule.setSalvageValue(0.0);
            rule.setCreatedAt(LocalDateTime.now());
            ruleRepository.save(rule);
        }
    }
}
