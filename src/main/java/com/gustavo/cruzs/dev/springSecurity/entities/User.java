package com.gustavo.cruzs.dev.springSecurity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gustavo.cruzs.dev.springSecurity.entities.enums.RolesUserEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class User implements Serializable, UserDetails {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  @JsonIgnore
  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private RolesUserEnum authority = RolesUserEnum.ROLE_COMMON;

  public User(UUID id, String name, String email, String password) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
  }

  @NullMarked
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority(authority.getValue()));
  }

  @NullMarked
  @Override
  public String getUsername() {
    return getEmail();
  }

  @NullMarked
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @NullMarked
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @NullMarked
  @Override
  public boolean isEnabled() {
    return true;
  }
}
