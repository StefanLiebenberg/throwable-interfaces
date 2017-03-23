package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.IntConsumerWithThrowable.aIntConsumerThatUnSafelyThrowsUncheckedThrowable;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class IntConsumerWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      aIntConsumerThatUnSafelyThrowsUncheckedThrowable((v1) -> {
        throw expected;
      }).accept(0);
      org.junit.Assert.fail("Exception should have been thrown");
    } catch (IOException e) {
      actual=e;
    }
    org.junit.Assert.assertEquals(expected, actual);
  }


  @Test
  public void testNormalOperation() {
    try {
      aIntConsumerThatUnSafelyThrowsUncheckedThrowable((v1) -> {
        if(false) throw new IOException();
      }).accept(0);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
