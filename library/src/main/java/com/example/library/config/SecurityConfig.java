package com.example.library.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.library.entity.RoleUser;


@Configuration
@EnableWebSecurity
public class SecurityConfig{


    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();  // Создание фильтра как бина
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http
        .httpBasic(httpBasic -> httpBasic.disable())
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize.requestMatchers("/books/*")
        .hasAnyAuthority(RoleUser.LIBRARIAN.name(), RoleUser.USER.name())
        .requestMatchers( "/admin/**").hasAuthority(RoleUser.LIBRARIAN.name())
        .requestMatchers("/register", "/auth").permitAll()
        .anyRequest().authenticated())
        .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
}
