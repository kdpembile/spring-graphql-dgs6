package com.kentisthebest.fake.datafetchers;

import com.kentisthebest.codegen.DgsConstants;
import com.kentisthebest.codegen.types.Contact;
import com.kentisthebest.codegen.types.relatedNameInput;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@DgsComponent
public class FakePhoneBookDataFetcher {

  private final List<Contact> contacts;

  public FakePhoneBookDataFetcher(List<Contact> contacts) {
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

  @DgsData(
      parentType = DgsConstants.QUERY_TYPE,
      field = DgsConstants.QUERY.ContactByRelatedName
  )
  public List<Contact> getContactByRelatedName(DataFetchingEnvironment dataFetchingEnvironment) {
    Map<String, Object> contactByRelatedNameMap = dataFetchingEnvironment
        .getArgument("relatedName");

    var contactByRelatedName = relatedNameInput
        .newBuilder()
        .firstName((String) contactByRelatedNameMap.get(DgsConstants.RELATEDNAMEINPUT.FirstName))
        .lastName((String) contactByRelatedNameMap.get(DgsConstants.RELATEDNAMEINPUT.LastName))
        .build();

    return contacts.stream()
        .filter(contact -> contact.getRelatedName()
            .stream()
            .anyMatch(person -> person.getFirstName().equals(contactByRelatedName.getFirstName())
                && person.getLastName().equals(contactByRelatedName.getLastName()))
        )
        .toList();
  }
}
