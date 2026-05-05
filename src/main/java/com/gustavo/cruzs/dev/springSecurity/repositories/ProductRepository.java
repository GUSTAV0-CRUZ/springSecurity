package com.gustavo.cruzs.dev.springSecurity.repositories;

import com.gustavo.cruzs.dev.springSecurity.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
