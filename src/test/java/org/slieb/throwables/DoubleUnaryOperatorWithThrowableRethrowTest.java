package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.DoubleUnaryOperatorWithThrowable.rethrowDoubleUnaryOperator;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class DoubleUnaryOperatorWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowDoubleUnaryOperator((v1) -> {
        throw expected;
      }).applyAsDouble(0);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowDoubleUnaryOperator((v1) -> {
        if(false) throw new IOException();
        return 0;
      }).applyAsDouble(0);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
