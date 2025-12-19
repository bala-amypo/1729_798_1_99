package com.example.demo.repository;
import com.example.demo.entity.User;
import com.example.demo.entity.Asset;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<Asset, Long> {
}
