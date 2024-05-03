package com.kentisthebest.resolvers;

import com.kentisthebest.codegen.types.Hello;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@DgsComponent
public class FakeHelloDataResolver {

  private final List<Hello> hellos;

  public FakeHelloDataResolver(List<Hello> hellos) {
    this.hellos = hellos;
  }

  @DgsQuery
  public List<Hello> allHellos() {
    return hellos;
  }

  @DgsQuery
  public Hello oneHello() {
    return hellos.get(ThreadLocalRandom.current().nextInt(hellos.size()));
  }
}
