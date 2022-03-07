package org.xander.practice.webapp.vuejs.controller;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xander.practice.webapp.vuejs.entity.User;
import org.xander.practice.webapp.vuejs.service.CustomOidcUserService;

@RestController
@RequestMapping("/api")
public class MainController {

  private final CustomOidcUserService userService;

  @Autowired
  public MainController(CustomOidcUserService userService) {
    this.userService = userService;
  }

  @GetMapping("/user/current")
  public @ResponseBody
  User main(KeycloakPrincipal<KeycloakSecurityContext> principal) {
    return userService.loadUser(principal.getKeycloakSecurityContext().getToken());
  }
}
