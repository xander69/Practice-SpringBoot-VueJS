package org.xander.practice.webapp.vuejs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/", "/login**", "/js/**", "/error**").permitAll()
        .anyRequest().authenticated()
        .and()
        .logout().logoutSuccessUrl("/").permitAll()
        .and()
        .oauth2Login()
        .and()
        .csrf().disable();
  }
}
