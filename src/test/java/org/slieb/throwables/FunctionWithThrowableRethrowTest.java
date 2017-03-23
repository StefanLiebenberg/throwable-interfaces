package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.FunctionWithThrowable.rethrowFunction;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class FunctionWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowFunction((v1) -> {
        throw expected;
      }).apply(null);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowFunction((v1) -> {
        if(false) throw new IOException();
        return null;
      }).apply(null);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
