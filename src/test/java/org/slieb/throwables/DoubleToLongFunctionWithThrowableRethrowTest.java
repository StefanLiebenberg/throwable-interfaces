package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.DoubleToLongFunctionWithThrowable.rethrowDoubleToLongFunction;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class DoubleToLongFunctionWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowDoubleToLongFunction((v1) -> {
        throw expected;
      }).applyAsLong(0);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowDoubleToLongFunction((v1) -> {
        if(false) throw new IOException();
        return 0;
      }).applyAsLong(0);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
