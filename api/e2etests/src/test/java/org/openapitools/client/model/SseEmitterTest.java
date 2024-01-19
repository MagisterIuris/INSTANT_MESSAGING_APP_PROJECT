package org.openapitools.client.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SseEmitterTest {
  private final SseEmitter model = new SseEmitter();
  @Test
  public void testSseEmitter() {
    assertNotNull(model);
  }
  @Test
  public void timeoutTest() {
    Long expectedTimeout = 1000L;
    model.setTimeout(expectedTimeout);
    Long actualTimeout = model.getTimeout();
    assertEquals(expectedTimeout, actualTimeout);
  }
}
