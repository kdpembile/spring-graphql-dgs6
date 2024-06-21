package com.kentisthebest.fake.datafetchers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.kentisthebest.codegen.client.ContactByRelatedNameGraphQLQuery;
import com.kentisthebest.codegen.client.PhonebookGraphQLQuery;
import com.kentisthebest.codegen.client.PhonebookProjectionRoot;
import com.kentisthebest.codegen.types.Contact;
import com.kentisthebest.codegen.types.relatedNameInput;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
import java.util.List;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FakePhoneBookDataFetcherTest {

  @Autowired
  DgsQueryExecutor dgsQueryExecutor;

  @Autowired
  List<Contact> contacts;

  @Test
  void phonebook() {
    var graphqlQuery = PhonebookGraphQLQuery.newRequest()
        .firstName(contacts.get(0).getPerson().getFirstName())
        .build();

    var projectionRoot = new PhonebookProjectionRoot<>()
        .person().firstName()
        .getRoot()
        .email().emailAddress();

    @Language("GraphQL")
    var graphqlQueryRequest = new GraphQLQueryRequest(graphqlQuery, projectionRoot).serialize();

    List<String> firstNames = dgsQueryExecutor
        .executeAndExtractJsonPath(graphqlQueryRequest, "data.phonebook[*].person.firstName");

    firstNames.forEach(System.out::println);

    assertNotNull(firstNames);
    assertFalse(firstNames.isEmpty());

    List<String> emailAddress = dgsQueryExecutor
        .executeAndExtractJsonPath(graphqlQueryRequest, "data.phonebook[*].email[*].emailAddress");

    assertNotNull(emailAddress);
    assertFalse(emailAddress.isEmpty());
  }

  @Test
  void getContactByRelatedName() {

    var relatedName = contacts.get(0).getRelatedName().get(0);

    var graphqlQuery = ContactByRelatedNameGraphQLQuery.newRequest()
        .relatedName(relatedNameInput.newBuilder()
            .firstName(relatedName.getFirstName())
            .lastName(relatedName.getLastName())
            .build())
        .build();

    var projectionRoot = new PhonebookProjectionRoot<>()
        .person().firstName().lastName();

    @Language("GraphQL")
    var graphqlQueryRequest = new GraphQLQueryRequest(graphqlQuery, projectionRoot).serialize();

    List<String> firstNames = dgsQueryExecutor
        .executeAndExtractJsonPath(graphqlQueryRequest, "data.contactByRelatedName[*].person.firstName");

    assertNotNull(firstNames);
    assertFalse(firstNames.isEmpty());

    List<String> lastNames = dgsQueryExecutor
        .executeAndExtractJsonPath(graphqlQueryRequest, "data.contactByRelatedName[*].person.lastName");

    assertNotNull(lastNames);
    assertFalse(lastNames.isEmpty());
  }
}