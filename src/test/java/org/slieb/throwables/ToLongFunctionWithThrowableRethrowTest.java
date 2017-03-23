package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.ToLongFunctionWithThrowable.rethrowToLongFunction;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class ToLongFunctionWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowToLongFunction((v1) -> {
        throw expected;
      }).applyAsLong(null);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowToLongFunction((v1) -> {
        if(false) throw new IOException();
        return 0;
      }).applyAsLong(null);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
