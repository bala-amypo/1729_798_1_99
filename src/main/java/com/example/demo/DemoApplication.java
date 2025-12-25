package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(
            RoleRepository roleRepo,
            UserRepository userRepo,
            VendorRepository vendorRepo,
            DepreciationRuleRepository ruleRepo,
            BCryptPasswordEncoder encoder
    ) {
        return args -> {

            Role adminRole = roleRepo.findByName("ADMIN")
                    .orElseGet(() -> roleRepo.save(new Role("ADMIN")));

            Role userRole = roleRepo.findByName("USER")
                    .orElseGet(() -> roleRepo.save(new Role("USER")));

            if (userRepo.findByEmail("integration_admin@example.com").isEmpty()) {
                User admin = new User();
                admin.setName("Admin");
                admin.setEmail("integration_admin@example.com");
                admin.setPassword(encoder.encode("adminpass"));
                admin.setRoles(Set.of(adminRole));
                userRepo.save(admin);
            }

            if (userRepo.findByEmail("integration_user@example.com").isEmpty()) {
                User user = new User();
                user.setName("User");
                user.setEmail("integration_user@example.com");
                user.setPassword(encoder.encode("userpass"));
                user.setRoles(Set.of(userRole));
                userRepo.save(user);
            }

            if (vendorRepo.findAll().isEmpty()) {
                Vendor v = new Vendor();
                v.setVendorName("IntegrationVendor");
                v.setContactEmail("vendor@test.com");
                vendorRepo.save(v);
            }

            if (ruleRepo.findAll().isEmpty()) {
                DepreciationRule r = new DepreciationRule();
                r.setRuleName("IntegrationRule");
                r.setMethod("STRAIGHT_LINE");
                r.setUsefulLifeYears(5);
                r.setSalvageValue(1000.0);
                ruleRepo.save(r);
            }
        };
    }
}
