package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.DoubleFunctionWithThrowable.rethrowDoubleFunction;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class DoubleFunctionWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      rethrowDoubleFunction((v1) -> {
        throw expected;
      }).apply(0);
      org.junit.Assert.fail("Exception should have been thrown");
    } catch (IOException e) {
      actual=e;
    }
    org.junit.Assert.assertEquals(expected, actual);
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowDoubleFunction((v1) -> {
        if(false) throw new IOException();
        return null;
      }).apply(0);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
