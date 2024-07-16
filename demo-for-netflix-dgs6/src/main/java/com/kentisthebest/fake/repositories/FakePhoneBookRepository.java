package com.kentisthebest.fake.repositories;

import com.kentisthebest.codegen.types.Address;
import com.kentisthebest.codegen.types.Contact;
import com.kentisthebest.codegen.types.Email;
import com.kentisthebest.codegen.types.Person;
import com.kentisthebest.codegen.types.Phone;
import com.kentisthebest.codegen.types.Ringtone;
import com.kentisthebest.codegen.types.Status;
import com.kentisthebest.codegen.types.TextTone;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakePhoneBookRepository {

  private final Faker faker;

  public FakePhoneBookRepository(Faker faker) {
    this.faker = faker;
  }

  @Bean
  List<String> phoneCategory() {
    return Arrays.asList("Work", "Home");
  }

  @Bean
  Random rand() {
    return new Random();
  }

  @Bean
  List<String> ringtoneCategory() {
    return Arrays.asList("Note", "Aurora", "Bamboo");
  }

  @Bean
  List<Contact> contacts() {

    List<Contact> list = new ArrayList<>();

    for (int i = 0; i < 20; i++) {
      var contacts = Contact.newBuilder()
          .person(Person.newBuilder()
              .firstName(faker.name().firstName())
              .lastName(faker.name().lastName())
              .birthday(faker.date().birthdayLocalDate())
              .address(
                  Arrays.asList(
                      Address.newBuilder()
                          .street(faker.address().streetAddress())
                          .city(faker.address().city())
                          .zipCode(faker.address().zipCode())
                          .country(faker.address().country())
                          .build(),
                      Address.newBuilder()
                          .street(faker.address().streetAddress())
                          .city(faker.address().city())
                          .zipCode(faker.address().zipCode())
                          .country(faker.address().country())
                          .build()
                  ))
              .build())
          .company(faker.company().name())
          .phone(
              Arrays.asList(
                  Phone.newBuilder()
                      .category(phoneCategory().get(rand().nextInt(phoneCategory().size() - 1)))
                      .number(faker.phoneNumber().phoneNumber())
                      .build(),
                  Phone.newBuilder()
                      .category(phoneCategory().get(rand().nextInt(phoneCategory().size() - 1)))
                      .number(faker.phoneNumber().phoneNumber())
                      .build())
          )
          .email(
              Arrays.asList(
                  Email.newBuilder()
                      .emailService(faker.internet().webdomain())
                      .emailAddress(faker.internet().emailAddress())
                      .build(),
                  Email.newBuilder()
                      .emailService(faker.internet().webdomain())
                      .emailAddress(faker.internet().emailAddress())
                      .build())
          )
          .ringtone(Collections.singletonList(
              Ringtone.newBuilder()
                  .name(ringtoneCategory().get(rand().nextInt(ringtoneCategory().size() - 2)))
                  .build()
          ))
          .textTone(Collections.singletonList(
              TextTone.newBuilder()
                  .name(ringtoneCategory().get(rand().nextInt(ringtoneCategory().size() - 2)))
                  .build()
          ))
          .relatedName(Collections.singletonList(
              Person.newBuilder()
                  .firstName(faker.name().firstName())
                  .lastName(faker.name().lastName())
                  .birthday(faker.date().birthdayLocalDate())
                  .address(
                      Arrays.asList(
                          Address.newBuilder()
                              .street(faker.address().streetAddress())
                              .city(faker.address().city())
                              .zipCode(faker.address().zipCode())
                              .country(faker.address().country())
                              .build(),
                          Address.newBuilder()
                              .street(faker.address().streetAddress())
                              .city(faker.address().city())
                              .zipCode(faker.address().zipCode())
                              .country(faker.address().country())
                              .build()
                      ))
                  .build()))
          .notes(faker.rickAndMorty().quote())
          .status(Status.ACTIVE)
          .build();

      list.add(contacts);
    }

    return list;
  }
}
