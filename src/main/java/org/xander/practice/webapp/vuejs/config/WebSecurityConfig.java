package org.xander.practice.webapp.vuejs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.xander.practice.webapp.vuejs.service.CustomOidcUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomOidcUserService customOidcUserService;

  @Autowired
  public WebSecurityConfig(CustomOidcUserService customOidcUserService) {
    this.customOidcUserService = customOidcUserService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .oauth2Login()
        .userInfoEndpoint()
        .oidcUserService(customOidcUserService);
  }
}
