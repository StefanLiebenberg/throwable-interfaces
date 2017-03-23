package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.DoubleConsumerWithThrowable.rethrowDoubleConsumer;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class DoubleConsumerWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowDoubleConsumer((v1) -> {
        throw expected;
      }).accept(0);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowDoubleConsumer((v1) -> {
        if(false) throw new IOException();
      }).accept(0);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
