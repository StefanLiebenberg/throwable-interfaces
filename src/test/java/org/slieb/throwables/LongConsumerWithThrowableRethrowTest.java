package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.LongConsumerWithThrowable.rethrowLongConsumer;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class LongConsumerWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowLongConsumer((v1) -> {
        throw expected;
      }).accept(0);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowLongConsumer((v1) -> {
        if(false) throw new IOException();
      }).accept(0);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
