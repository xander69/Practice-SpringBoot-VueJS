package org.xander.practice.webapp.vuejs.service;

import org.springframework.stereotype.Service;
import org.xander.practice.webapp.vuejs.entity.User;
import org.xander.practice.webapp.vuejs.repository.UserRepository;

import java.util.Set;

@Service
public class ProfileService {

  private final UserRepository userRepository;

  public ProfileService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User changeSubscription(User channel, User subscriber) {
    Set<User> subscribers = channel.getSubscribers();
    if (subscribers.contains(subscriber)) {
      subscribers.remove(subscriber);
    } else {
      subscribers.add(subscriber);
    }
    return userRepository.save(channel);
  }
}
