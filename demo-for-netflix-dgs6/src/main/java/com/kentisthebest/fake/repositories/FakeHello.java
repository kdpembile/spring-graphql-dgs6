package com.kentisthebest.fake.repositories;

import com.kentisthebest.codegen.types.Hello;
import java.util.ArrayList;
import java.util.List;
import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakeHello {

  private final Faker faker;

  public FakeHello(Faker faker) {
    this.faker = faker;
  }

  @Bean
  List<Hello> hellos() {
    List<Hello> list = new ArrayList<>();

    for (int i = 0; i < 20; i++) {
      var hello = Hello.newBuilder()
          .randomNumber(faker.random().nextInt(5000))
          .text(faker.funnyName().name())
          .build();

      list.add(hello);
    }

    return list;
  }
}
