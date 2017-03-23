package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.DoubleToIntFunctionWithThrowable.rethrowDoubleToIntFunction;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class DoubleToIntFunctionWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowDoubleToIntFunction((v1) -> {
        throw expected;
      }).applyAsInt(0);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowDoubleToIntFunction((v1) -> {
        if(false) throw new IOException();
        return 0;
      }).applyAsInt(0);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
