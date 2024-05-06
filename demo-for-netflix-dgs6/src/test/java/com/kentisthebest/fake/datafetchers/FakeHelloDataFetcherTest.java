package com.kentisthebest.fake.datafetchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import java.util.List;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FakeHelloDataFetcherTest {

  @Autowired
  private DgsQueryExecutor dgsQueryExecutor;

  @Test
  void allHellos() {
    @Language("GraphQL") var graphQLQuery = """
        query AllHellos {
            allHellos {
                text
                randomNumber
            }
        }
        """;

    List<String> texts = dgsQueryExecutor.executeAndExtractJsonPath(graphQLQuery, "data.allHellos[*].text");
    List<Integer> randomNumbers = dgsQueryExecutor.executeAndExtractJsonPath(graphQLQuery, "data.allHellos[*].randomNumber");

    assertNotNull(texts);
    assertFalse(texts.isEmpty());
    assertNotNull(randomNumbers);
    assertEquals(texts.size(), randomNumbers.size());
  }

  @Test
  void oneHello() {
    @Language("GraphQL") var graphQLQuery = """
        query OneHello {
            oneHello {
                text
                randomNumber
            }
        }
                
        """;
    String text = dgsQueryExecutor.executeAndExtractJsonPath(graphQLQuery, "data.oneHello.text");
    Integer randomNumber = dgsQueryExecutor.executeAndExtractJsonPath(graphQLQuery, "data.oneHello.randomNumber");

    assertFalse(StringUtils.isBlank(text));
    assertNotNull(randomNumber);
  }
}