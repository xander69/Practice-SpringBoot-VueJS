package org.xander.practice.webapp.vuejs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xander.practice.webapp.vuejs.repository.UserRepository;
import org.xander.practice.webapp.vuejs.service.MessageService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class MainController {

  private final MessageService messageService;
  private final UserRepository userRepository;

  @Autowired
  public MainController(MessageService messageService, UserRepository userRepository) {
    this.messageService = messageService;
    this.userRepository = userRepository;
  }

  @GetMapping
  public String main(Model model,
                     @AuthenticationPrincipal OAuth2User user) {
    Map<Object, Object> frontendData = new HashMap<>();
    frontendData.put("profile", Optional.ofNullable(user)
        .flatMap(u -> userRepository.findById(u.getName()))
        .orElse(null));
    frontendData.put("messages", messageService.getAllMessages());
    model.addAttribute("frontendData", frontendData);
    return "index";
  }
}
