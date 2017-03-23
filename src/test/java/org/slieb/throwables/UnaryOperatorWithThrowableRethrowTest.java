package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.UnaryOperatorWithThrowable.rethrowUnaryOperator;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class UnaryOperatorWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowUnaryOperator((v1) -> {
        throw expected;
      }).apply(null);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowUnaryOperator((v1) -> {
        if(false) throw new IOException();
        return null;
      }).apply(null);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
