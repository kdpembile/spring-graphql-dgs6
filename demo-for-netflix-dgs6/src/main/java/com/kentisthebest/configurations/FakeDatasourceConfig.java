package com.kentisthebest.configurations;

import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakeDatasourceConfig {

  @Bean
  Faker faker() {
    return new Faker();
  }

}
