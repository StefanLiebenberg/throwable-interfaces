package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.ToLongFunctionWithThrowable.aToLongFunctionThatUnsafelyThrowsUnchecked;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class ToLongFunctionWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      aToLongFunctionThatUnsafelyThrowsUnchecked((v1) -> {
        throw expected;
      }).applyAsLong(null);
      org.junit.Assert.fail("Exception should have been thrown");
    } catch (IOException e) {
      actual=e;
    }
    org.junit.Assert.assertEquals(expected, actual);
  }


  @Test
  public void testNormalOperation() {
    try {
      aToLongFunctionThatUnsafelyThrowsUnchecked((v1) -> {
        if(false) throw new IOException();
        return 0;
      }).applyAsLong(null);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
