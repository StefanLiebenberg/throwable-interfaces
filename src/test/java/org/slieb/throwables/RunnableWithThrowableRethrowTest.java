package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.RunnableWithThrowable.aRunnableThatUnsafelyThrowsUnchecked;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class RunnableWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      aRunnableThatUnsafelyThrowsUnchecked(() -> {
        throw expected;
      }).run();
      org.junit.Assert.fail("Exception should have been thrown");
    } catch (IOException e) {
      actual=e;
    }
    org.junit.Assert.assertEquals(expected, actual);
  }


  @Test
  public void testNormalOperation() {
    try {
      aRunnableThatUnsafelyThrowsUnchecked(() -> {
        if(false) throw new IOException();
      }).run();
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
