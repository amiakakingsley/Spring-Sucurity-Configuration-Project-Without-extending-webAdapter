package com.amiaka.malvin.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

     private final String[] authorizeURL = { "/login", "/register" };

   

     @Bean
     PasswordEncoder passwordEncoder() {
          return new BCryptPasswordEncoder();
     }

     @Bean
     AuthenticationManager authenticator(UserDetailsService userDetailsService){
          DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
          daoAuthenticationProvider.setUserDetailsService(userDetailsService);
          return new ProviderManager(daoAuthenticationProvider);
     }
     @Bean
     SecurityFilterChain filter(HttpSecurity http) throws Exception {
          return http.csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(auth -> auth
                    .requestMatchers(authorizeURL).permitAll()
                    .requestMatchers("/user/**").authenticated()
                    .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")      
          )
                    .formLogin(formlogin -> formlogin
                              .loginPage("/login")
                              .loginProcessingUrl("/login")
                              .failureUrl("/error")
                              .passwordParameter("password")
                              .usernameParameter("email")
                              .defaultSuccessUrl("/user/home"))
                    .logout(logout -> logout
                              .logoutUrl("/logout")
                              .invalidateHttpSession(true)
                              .deleteCookies("JSESSIONID")
                              .logoutSuccessUrl("/login"))
                    .build();
     }



}
