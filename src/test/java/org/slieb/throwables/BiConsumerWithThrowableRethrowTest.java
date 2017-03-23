package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.BiConsumerWithThrowable.rethrowBiConsumer;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class BiConsumerWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      rethrowBiConsumer((v1, v2) -> {
        throw expected;
      }).accept(null, null);
      org.junit.Assert.fail("Exception should have been thrown");
    } catch (IOException e) {
      actual=e;
    }
    org.junit.Assert.assertEquals(expected, actual);
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowBiConsumer((v1, v2) -> {
        if(false) throw new IOException();
      }).accept(null, null);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
