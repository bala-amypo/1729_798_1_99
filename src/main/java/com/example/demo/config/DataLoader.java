package com.example.demo.config;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.Vendor;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.DepreciationRuleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(
            RoleRepository roleRepo,
            UserRepository userRepo,
            VendorRepository vendorRepo,
            DepreciationRuleRepository ruleRepo,
            PasswordEncoder encoder
    ) {
        return args -> {

            // Roles
            Role adminRole = roleRepo.findByName("ADMIN")
                    .orElseGet(() -> roleRepo.save(new Role("ADMIN")));

            Role userRole = roleRepo.findByName("USER")
                    .orElseGet(() -> roleRepo.save(new Role("USER")));

            // Admin user
            if (userRepo.findByEmail("integration_admin@example.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("integration_admin@example.com");
                admin.setPassword(encoder.encode("adminpass"));
                admin.setRoles(Set.of(adminRole));
                userRepo.save(admin);
            }

            // Normal user
            if (userRepo.findByEmail("integration_user@example.com").isEmpty()) {
                User user = new User();
                user.setEmail("integration_user@example.com");
                user.setPassword(encoder.encode("userpass"));
                user.setRoles(Set.of(userRole));
                userRepo.save(user);
            }

            // Vendor
            vendorRepo.findByVendorName("IntegrationVendor")
                    .orElseGet(() -> {
                        Vendor v = new Vendor();
                        v.setVendorName("IntegrationVendor");
                        v.setContactEmail("vendor@example.com");
                        return vendorRepo.save(v);
                    });

            // Depreciation Rule
            ruleRepo.findByRuleName("IntegrationRule")
                    .orElseGet(() -> {
                        DepreciationRule r = new DepreciationRule();
                        r.setRuleName("IntegrationRule");
                        r.setMethod("STRAIGHT_LINE");
                        r.setUsefulLifeYears(5);
                        r.setSalvageValue(0.0);
                        return ruleRepo.save(r);
                    });
        };
    }
}
