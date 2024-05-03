package com.kentisthebest.datasource.fake;

import com.kentisthebest.codegen.types.Hello;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakeHelloDataSource {

  @Autowired
  private Faker faker;

  public static final List<Hello> HELLOS = new ArrayList<>();

  @PostConstruct
  private void postConstruct() {
    for (int i = 0; i < 20; i++) {
      var hello = Hello.newBuilder()
          .randomNumber(faker.random().nextInt(5000))
          .text(faker.funnyName().name())
          .build();

      HELLOS.add(hello);
    }
  }

}
