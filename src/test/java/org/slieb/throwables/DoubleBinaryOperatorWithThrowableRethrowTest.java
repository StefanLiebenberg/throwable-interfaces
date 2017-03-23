package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.DoubleBinaryOperatorWithThrowable.rethrowDoubleBinaryOperator;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class DoubleBinaryOperatorWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowDoubleBinaryOperator((v1, v2) -> {
        throw expected;
      }).applyAsDouble(0, 0);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowDoubleBinaryOperator((v1, v2) -> {
        if(false) throw new IOException();
        return 0;
      }).applyAsDouble(0, 0);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
