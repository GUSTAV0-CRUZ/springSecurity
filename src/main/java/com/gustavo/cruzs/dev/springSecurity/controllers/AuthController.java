package com.gustavo.cruzs.dev.springSecurity.controllers;

import com.gustavo.cruzs.dev.springSecurity.dtos.CreateUserDto;
import com.gustavo.cruzs.dev.springSecurity.dtos.LoginDto;
import com.gustavo.cruzs.dev.springSecurity.entities.User;
import com.gustavo.cruzs.dev.springSecurity.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "auth")
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping(value = "/register")
  public ResponseEntity<User> register(@RequestBody CreateUserDto createUserDto) {
    var user = this.authService.register(createUserDto);

    return ResponseEntity.status(201).body(user);
  }

  @PostMapping(value = "/login")
  public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
     var jwt = this.authService.login(loginDto);

    return ResponseEntity.ok().body(jwt);
  }
}
