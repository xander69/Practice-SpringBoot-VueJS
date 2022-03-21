package org.xander.practice.webapp.vuejs;

import io.sentry.Sentry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    Sentry.captureMessage("Starting application...");
    SpringApplication.run(Application.class, args);
  }

}
