package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.LongBinaryOperatorWithThrowable.rethrowLongBinaryOperator;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class LongBinaryOperatorWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowLongBinaryOperator((v1, v2) -> {
        throw expected;
      }).applyAsLong(0, 0);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowLongBinaryOperator((v1, v2) -> {
        if(false) throw new IOException();
        return 0;
      }).applyAsLong(0, 0);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
