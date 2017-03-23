package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.RunnableWithThrowable.rethrowRunnable;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class RunnableWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowRunnable(() -> {
        throw expected;
      }).run();
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowRunnable(() -> {
        if(false) throw new IOException();
      }).run();
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
