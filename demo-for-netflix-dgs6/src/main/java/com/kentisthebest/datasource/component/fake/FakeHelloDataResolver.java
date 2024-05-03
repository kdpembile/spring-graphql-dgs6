package com.kentisthebest.datasource.component.fake;

import com.kentisthebest.codegen.types.Hello;
import com.kentisthebest.datasource.fake.FakeHelloDataSource;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@DgsComponent
public class FakeHelloDataResolver {

  @DgsQuery
  public List<Hello> allHellos() {
    return FakeHelloDataSource.HELLOS;
  }

  @DgsQuery
  public Hello oneHello() {
    return FakeHelloDataSource.HELLOS.get(
        ThreadLocalRandom.current().nextInt(FakeHelloDataSource.HELLOS.size())
    );
  }
}
