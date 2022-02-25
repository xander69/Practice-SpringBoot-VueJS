package org.xander.practice.webapp.vuejs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.xander.practice.webapp.vuejs.entity.User;
import org.xander.practice.webapp.vuejs.repository.UserRepository;

import java.util.Date;
import java.util.Map;

@Service
public class CustomOidcUserService extends OidcUserService {

  private final UserRepository userRepository;

  @Autowired
  public CustomOidcUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
    OidcUser oidcUser = super.loadUser(userRequest);
    Map<String, Object> attributes = oidcUser.getAttributes();
    String id = (String) attributes.get("sub");
    User user = userRepository.findById(id).orElseGet(() -> {
      User newUser = new User();
      newUser.setId(id);
      newUser.setName((String) attributes.get("name"));
      newUser.setEmail((String) attributes.get("email"));
      newUser.setGender((String) attributes.get("gender"));
      newUser.setLocale((String) attributes.get("locale"));
      newUser.setUserpic((String) attributes.get("picture"));
      return newUser;
    });
    user.setLastVisit(new Date());
    userRepository.save(user);
    return oidcUser;
  }
}
