package scaper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

public class Application {

  private final static Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }

  //@Bean

}
