package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.DoubleUnaryOperatorWithThrowable.aDoubleUnaryOperatorThatUnSafelyThrowsUncheckedThrowable;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class DoubleUnaryOperatorWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      aDoubleUnaryOperatorThatUnSafelyThrowsUncheckedThrowable((v1) -> {
        throw expected;
      }).applyAsDouble(0);
      org.junit.Assert.fail("Exception should have been thrown");
    } catch (IOException e) {
      actual=e;
    }
    org.junit.Assert.assertEquals(expected, actual);
  }


  @Test
  public void testNormalOperation() {
    try {
      aDoubleUnaryOperatorThatUnSafelyThrowsUncheckedThrowable((v1) -> {
        if(false) throw new IOException();
        return 0;
      }).applyAsDouble(0);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
