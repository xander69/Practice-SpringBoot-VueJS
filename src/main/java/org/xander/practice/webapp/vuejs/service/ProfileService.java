package org.xander.practice.webapp.vuejs.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xander.practice.webapp.vuejs.entity.User;
import org.xander.practice.webapp.vuejs.entity.UserSubscription;
import org.xander.practice.webapp.vuejs.repository.UserRepository;
import org.xander.practice.webapp.vuejs.repository.UserSubscriptionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {

  private final UserRepository userRepository;
  private final UserSubscriptionRepository userSubscriptionRepository;

  public ProfileService(UserRepository userRepository,
                        UserSubscriptionRepository userSubscriptionRepository) {
    this.userRepository = userRepository;
    this.userSubscriptionRepository = userSubscriptionRepository;
  }

  @Transactional
  public User changeSubscription(User channel, User subscriber) {
    List<UserSubscription> subscriptions = channel.getSubscribers().stream()
        .filter(subscription -> subscription.getSubscriber().equals(subscriber))
        .collect(Collectors.toList());
    if (subscriptions.isEmpty()) {
      UserSubscription subscription = new UserSubscription(channel, subscriber);
      subscription.setActive(Boolean.FALSE);
      channel.getSubscribers().add(subscription);
    } else {
      subscriptions.forEach(channel.getSubscribers()::remove);
    }
    return userRepository.save(channel);
  }

  public List<UserSubscription> getSubscribers(User channel) {
    return userSubscriptionRepository.findByChannel(channel);
  }

  @Transactional
  public UserSubscription changeSubscriptionStatus(User channel, User subscriber) {
    UserSubscription subscription = userSubscriptionRepository.findByChannelAndSubscriber(channel, subscriber);
    subscription.setActive(!subscription.getActive());
    return userSubscriptionRepository.save(subscription);
  }
}
