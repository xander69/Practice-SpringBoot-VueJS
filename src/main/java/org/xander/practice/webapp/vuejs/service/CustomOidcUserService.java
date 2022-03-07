package org.xander.practice.webapp.vuejs.service;

import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xander.practice.webapp.vuejs.entity.User;
import org.xander.practice.webapp.vuejs.repository.UserRepository;

import java.util.Date;

@Service
public class CustomOidcUserService {

  private final UserRepository userRepository;

  @Autowired
  public CustomOidcUserService(UserRepository userRepository) {
    this.userRepository = userRepository;

  }

  @Transactional
  public User loadUser(AccessToken accessToken) {
    String id = accessToken.getSubject();
    User user = userRepository.findById(id).orElseGet(() -> {
      User newUser = new User();
      newUser.setId(id);
      newUser.setName(accessToken.getName());
      newUser.setEmail(accessToken.getEmail());
      newUser.setGender(accessToken.getGender());
      newUser.setLocale(accessToken.getLocale());
      newUser.setUserpic(accessToken.getPicture());
      return newUser;
    });
    user.setLastVisit(new Date());
    return userRepository.save(user);
  }
}
