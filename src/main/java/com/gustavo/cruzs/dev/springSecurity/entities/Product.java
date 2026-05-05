package com.gustavo.cruzs.dev.springSecurity.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@ToString
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  UUID id;
  @Column(nullable = false)
  String name;
  @Column(nullable = false)
  Double price;

  public Product(UUID id, String name, Double price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  public void changeName(String name) {
    if(name.length() < 3) throw new IllegalArgumentException("Name must be longer 3");

    this.name = name;
  }

  public void changePrice(Double price) {
    if(price < 0) throw new IllegalArgumentException("Price cannot be less than 0");

    this.price = price;
  }
}
