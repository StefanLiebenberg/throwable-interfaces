package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.ToIntFunctionWithThrowable.rethrowToIntFunction;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class ToIntFunctionWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    try {
      rethrowToIntFunction((v1) -> {
        throw expected;
      }).applyAsInt(null);
    } catch (IOException actual) {
      org.junit.Assert.assertEquals(expected, actual);
    }
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowToIntFunction((v1) -> {
        if(false) throw new IOException();
        return 0;
      }).applyAsInt(null);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
