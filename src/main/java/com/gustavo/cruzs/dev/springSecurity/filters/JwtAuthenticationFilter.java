package com.gustavo.cruzs.dev.springSecurity.filters;

import com.gustavo.cruzs.dev.springSecurity.repositories.UserRepository;
import com.gustavo.cruzs.dev.springSecurity.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtService jwtService;
  private final UserRepository userRepository;

  public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
    this.jwtService = jwtService;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
  ) throws ServletException, IOException {
    String token = request.getHeader("Authorization");

    if(token != null && token.startsWith("Bearer ")) {
      String tokenExtracted = token.replace("Bearer ", "");
      String email = jwtService.validateToken(tokenExtracted);

      userRepository.findByEmail(email).ifPresent(user -> {
          var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authentication);
         }
      );
    }

    filterChain.doFilter(request, response);
  }
}
