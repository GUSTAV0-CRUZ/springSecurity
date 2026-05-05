package com.gustavo.cruzs.dev.springSecurity.controllers;

import com.gustavo.cruzs.dev.springSecurity.dtos.CreateProductDto;
import com.gustavo.cruzs.dev.springSecurity.entities.Product;
import com.gustavo.cruzs.dev.springSecurity.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "product")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<List<Product>> findAll() {
    return ResponseEntity.ok().body(this.productService.findAll());
  }

  public ResponseEntity<Product> create(CreateProductDto createProductDto) {
    var product = this.productService.create(createProductDto);
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(product.getId())
        .toUri();;

    return ResponseEntity.created(uri).body(product);
  }
}
