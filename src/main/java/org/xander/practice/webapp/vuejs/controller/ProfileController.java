package org.xander.practice.webapp.vuejs.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xander.practice.webapp.vuejs.entity.User;
import org.xander.practice.webapp.vuejs.entity.Views;
import org.xander.practice.webapp.vuejs.service.CustomOidcUserService;
import org.xander.practice.webapp.vuejs.service.ProfileService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

  private final ProfileService profileService;
  private final CustomOidcUserService userService;

  public ProfileController(ProfileService profileService,
                           CustomOidcUserService userService) {
    this.profileService = profileService;
    this.userService = userService;
  }

  @GetMapping
  @JsonView(Views.FullProfile.class)
  public @ResponseBody User currentUser(KeycloakPrincipal<KeycloakSecurityContext> principal) {
    return userService.loadUser(principal.getKeycloakSecurityContext().getToken());
  }

  @GetMapping("/{id}")
  @JsonView(Views.FullProfile.class)
  public @ResponseBody
  User get(@PathVariable("id") User user) {
    return user;
  }

  @PostMapping("/change-subscription/{channelId}")
  @JsonView(Views.FullProfile.class)
  public @ResponseBody User changeSubscription(
      KeycloakPrincipal<KeycloakSecurityContext> principal,
      @PathVariable("channelId") User channel
  ) {
    User subscriber = userService.loadUser(principal.getKeycloakSecurityContext().getToken());
    if (subscriber.equals(channel)) {
      return channel;
    }
    return profileService.changeSubscription(channel, subscriber);
  }
}
