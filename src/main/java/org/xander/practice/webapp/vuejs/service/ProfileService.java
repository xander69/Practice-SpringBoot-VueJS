package org.xander.practice.webapp.vuejs.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xander.practice.webapp.vuejs.entity.User;
import org.xander.practice.webapp.vuejs.entity.UserSubscription;
import org.xander.practice.webapp.vuejs.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {

  private final UserRepository userRepository;

  public ProfileService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public User changeSubscription(User channel, User subscriber) {
    List<UserSubscription> subscriptions = channel.getSubscribers().stream()
        .filter(subscription -> subscription.getSubscriber().equals(subscriber))
        .collect(Collectors.toList());
    if (subscriptions.isEmpty()) {
      UserSubscription subscription = new UserSubscription(channel, subscriber);
      channel.getSubscribers().add(subscription);
    } else {
      subscriptions.forEach(channel.getSubscribers()::remove);
    }
    return userRepository.save(channel);
  }
}
