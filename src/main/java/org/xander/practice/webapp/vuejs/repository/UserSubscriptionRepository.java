package org.xander.practice.webapp.vuejs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xander.practice.webapp.vuejs.entity.User;
import org.xander.practice.webapp.vuejs.entity.UserSubscription;
import org.xander.practice.webapp.vuejs.entity.UserSubscriptionId;

import java.util.List;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, UserSubscriptionId> {
  List<UserSubscription> findBySubscriber(User subscriber);

  List<UserSubscription> findByChannel(User channel);

  UserSubscription findByChannelAndSubscriber(User channel, User subscriber);
}
