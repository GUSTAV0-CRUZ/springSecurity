package com.gustavo.cruzs.dev.springSecurity.configs;

import com.gustavo.cruzs.dev.springSecurity.entities.enums.RolesUserEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
    return http
        .formLogin(form -> form.disable())
        .httpBasic((httpBasic -> httpBasic.disable()))
        .csrf((csrf) -> csrf.disable())
        .sessionManagement(
            s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.GET, "/product/**").hasAnyAuthority(
                RolesUserEnum.ROLE_COMMON.getValue(), RolesUserEnum.ROLE_ADMIN.getValue()
            )
            .requestMatchers("/product/**").hasAnyAuthority(
                RolesUserEnum.ROLE_ADMIN.getValue()
            )
            .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
            .anyRequest().authenticated()
        )
        .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
