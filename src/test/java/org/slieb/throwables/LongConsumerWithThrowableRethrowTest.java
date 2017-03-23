package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.LongConsumerWithThrowable.aLongConsumerThatUnSafelyThrowsUncheckedThrowable;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class LongConsumerWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      aLongConsumerThatUnSafelyThrowsUncheckedThrowable((v1) -> {
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
      aLongConsumerThatUnSafelyThrowsUncheckedThrowable((v1) -> {
        if(false) throw new IOException();
      }).accept(0);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
