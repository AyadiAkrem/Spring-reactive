package com.itdeep.reactive.security;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf().requireCsrfProtectionMatcher(
                serverWebExchange ->
                        ServerWebExchangeMatchers.pathMatchers("/urls-with-csrf-check/**").matches(serverWebExchange))
                .and()
                .authorizeExchange()
                .pathMatchers("/login", "/css/***", "/vendor/**", "/js/**", "/user/add").permitAll()
                .anyExchange().authenticated()
                .and()
                .httpBasic().and()
                .formLogin().loginPage("/login");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
