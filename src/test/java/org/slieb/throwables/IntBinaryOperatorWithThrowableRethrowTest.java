package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.IntBinaryOperatorWithThrowable.rethrowIntBinaryOperator;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class IntBinaryOperatorWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      rethrowIntBinaryOperator((v1, v2) -> {
        throw expected;
      }).applyAsInt(0, 0);
      org.junit.Assert.fail("Exception should have been thrown");
    } catch (IOException e) {
      actual=e;
    }
    org.junit.Assert.assertEquals(expected, actual);
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowIntBinaryOperator((v1, v2) -> {
        if(false) throw new IOException();
        return 0;
      }).applyAsInt(0, 0);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
