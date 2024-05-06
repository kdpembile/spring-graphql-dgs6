package com.kentisthebest.fake.resolvers;

import com.kentisthebest.codegen.types.Contact;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import java.util.List;
import java.util.Optional;

@DgsComponent
public class FakePhoneBookResolver {

  private final List<Contact> contacts;

  public FakePhoneBookResolver(List<Contact> contacts) {
    this.contacts = contacts;
  }

  @DgsQuery
  public List<Contact> phonebook(@InputArgument Optional<String> firstName) {
    return firstName
        .map(s -> contacts
            .stream()
            .filter(contact -> contact.getPerson().getFirstName().equals(s))
            .toList())
        .orElse(contacts);
  }
}
