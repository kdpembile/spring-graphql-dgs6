package com.kentisthebest.fake.mutations;

import com.kentisthebest.codegen.types.Hello;
import com.kentisthebest.codegen.types.HelloInput;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import java.util.List;
import java.util.Objects;

@DgsComponent
public class FakeHelloMutation {

  private final List<Hello> hellos;

  public FakeHelloMutation(List<Hello> hellos) {
    this.hellos = hellos;
  }

  @DgsMutation
  public int addHello(@InputArgument(name = "helloInput") HelloInput helloInput) {
    hellos.add(Hello.newBuilder()
        .text(helloInput.getText())
        .randomNumber(helloInput.getRandomNumber())
        .build());

    return hellos.size();
  }

  @DgsMutation
  public List<Hello> replaceHelloText(@InputArgument(name = "helloInput") HelloInput helloInput) {
    hellos.stream()
        .filter(hello -> hello.getRandomNumber() == helloInput.getRandomNumber())
        .forEach(hello -> hello.setText(helloInput.getText()));

    return hellos;
  }

  @DgsMutation
  public int deleteHello(@InputArgument Integer number) {
    hellos.removeIf(hello -> Objects.equals(hello.getRandomNumber(), number));

    return hellos.size();
  }

}
