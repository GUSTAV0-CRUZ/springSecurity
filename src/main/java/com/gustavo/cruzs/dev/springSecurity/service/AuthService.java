package com.gustavo.cruzs.dev.springSecurity.service;

import com.gustavo.cruzs.dev.springSecurity.dtos.CreateUserDto;
import com.gustavo.cruzs.dev.springSecurity.dtos.LoginDto;
import com.gustavo.cruzs.dev.springSecurity.entities.User;
import com.gustavo.cruzs.dev.springSecurity.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.jwtService = jwtService;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
  }

  public User register(CreateUserDto createUserDto) {
    var password = passwordEncoder.encode(createUserDto.password());

    var user = new User(
        null, createUserDto.name(), createUserDto.email(), password
    );

    return userRepository.save(user);
  }

  public String login(LoginDto loginDto) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());
    var auth = authenticationManager.authenticate(usernamePassword);
    var user = (User) auth.getPrincipal();

    return  this.jwtService.create(user);
  }
}
