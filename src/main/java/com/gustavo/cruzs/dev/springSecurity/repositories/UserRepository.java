package com.gustavo.cruzs.dev.springSecurity.repositories;

import com.gustavo.cruzs.dev.springSecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByEmail(String email);
}
