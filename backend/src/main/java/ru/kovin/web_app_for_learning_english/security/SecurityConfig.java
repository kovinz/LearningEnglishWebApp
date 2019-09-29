package ru.kovin.web_app_for_learning_english.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userRepositoryUserDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth)
          throws Exception {
    auth
            .userDetailsService(userRepositoryUserDetailsService)
            .passwordEncoder(encoder())
    ;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .antMatchers("/idk")
            .access("hasRole('ROLE_USER')")
            .antMatchers("/", "/**").access("permitAll")

            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/", true)

            .and()
            .logout()
            .logoutSuccessUrl("/")
    ;
  }

  @Bean
  public PasswordEncoder encoder() {
    return new StandardPasswordEncoder("53cr3t");
  }
}