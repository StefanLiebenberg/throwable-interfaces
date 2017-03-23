package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.BiConsumerWithThrowable.rethrowBiConsumer;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class BiConsumerWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowBiConsumer((v1, v2) -> {
        throw expected;
      }).accept(null, null);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
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
