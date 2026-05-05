package com.gustavo.cruzs.dev.springSecurity.service;

import com.gustavo.cruzs.dev.springSecurity.dtos.CreateProductDto;
import com.gustavo.cruzs.dev.springSecurity.entities.Product;
import com.gustavo.cruzs.dev.springSecurity.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> findAll() {
    return this.productRepository.findAll();
  }

  public Product create(CreateProductDto createProductDto) {
    Product product = new Product(
        null,
        createProductDto.name(),
        createProductDto.price()
    );

    return this.productRepository.save(product);
  }
}
