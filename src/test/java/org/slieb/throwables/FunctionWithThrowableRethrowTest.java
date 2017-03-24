package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.FunctionWithThrowable.aFunctionThatUnsafelyThrowsUnchecked;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class FunctionWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      aFunctionThatUnsafelyThrowsUnchecked((v1) -> {
        throw expected;
      }).apply(null);
      org.junit.Assert.fail("Exception should have been thrown");
    } catch (IOException e) {
      actual=e;
    }
    org.junit.Assert.assertEquals(expected, actual);
  }


  @Test
  public void testNormalOperation() {
    try {
      aFunctionThatUnsafelyThrowsUnchecked((v1) -> {
        if(false) throw new IOException();
        return null;
      }).apply(null);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
