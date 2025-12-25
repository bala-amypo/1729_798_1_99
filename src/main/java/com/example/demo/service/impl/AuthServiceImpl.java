package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponse register(AuthRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        Role userRole = roleRepository.findByName("USER")
            .orElseGet(() -> roleRepository.save(new Role("USER")));
        user.getRoles().add(userRole);
        
        user = userRepository.save(user);
        
        Set<String> roles = user.getRoles().stream()
            .map(Role::getName)
            .collect(Collectors.toSet());
        
        String token = jwtUtil.generateToken(user.getEmail(), user.getId(), roles);
        return new AuthResponse(token, user.getEmail(), user.getId());
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        
        Set<String> roles = user.getRoles().stream()
            .map(Role::getName)
            .collect(Collectors.toSet());
        
        String token = jwtUtil.generateToken(user.getEmail(), user.getId(), roles);
        return new AuthResponse(token, user.getEmail(), user.getId());
    }
}