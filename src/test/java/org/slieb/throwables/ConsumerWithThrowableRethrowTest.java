package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.ConsumerWithThrowable.rethrowConsumer;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class ConsumerWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowConsumer((v1) -> {
        throw expected;
      }).accept(null);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowConsumer((v1) -> {
        if(false) throw new IOException();
      }).accept(null);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
